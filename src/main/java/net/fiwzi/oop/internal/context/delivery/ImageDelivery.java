package net.fiwzi.oop.internal.context.delivery;

import jakarta.validation.constraints.NotBlank;
import net.fiwzi.oop.internal.context.usecase.ImageUsecase;
import net.fiwzi.oop.utils.PathList;
import net.fiwzi.oop.utils.SecurityEscape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

@RestController
public class ImageDelivery {

    @Autowired
    private ImageUsecase imageUsecase;

    @GetMapping("/image")
    public ResponseEntity<Resource> getDicomImageDelivery(@RequestParam("filename") String filename) {
        try {
            Resource resource = imageUsecase.getDicomFileUsecase(SecurityEscape.Security(filename));
            return ResponseEntity.ok()
                    .body(resource);
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/file/list")
    public ResponseEntity<?> getImageListDelivery(@RequestParam(defaultValue = "/train_images/10728036/142859125",required = false,name = "path") String path) {
        try {
            return ResponseEntity.ok().body(imageUsecase.getImageListUsecase(SecurityEscape.Security(path)));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/path/list")
    public ResponseEntity<?> getImagePathDelivery(@RequestParam(name = "start", defaultValue = "0") int start) {
        return ResponseEntity.ok().body(imageUsecase.getImagePathUsecase(start));
    }
}
