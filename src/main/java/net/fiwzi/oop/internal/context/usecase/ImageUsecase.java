package net.fiwzi.oop.internal.context.usecase;

import net.fiwzi.oop.infrastructure.Constant;
import net.fiwzi.oop.internal.context.interfaces.DynamicObjectInterface;
import net.fiwzi.oop.internal.context.interfaces.ResponseInterface;
import net.fiwzi.oop.utils.DirList;
import net.fiwzi.oop.utils.PathList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;
//import java.util.URL;
import java.net.*;
@Service
public class ImageUsecase {
    @Autowired
    private ResourceLoader resourceLoader;

    @Cacheable(value = "dicomCache", key = "#root.args[0]")
    public Resource getDicomFileUsecase(String filename) throws IOException {
        Resource resource = resourceLoader.getResource("file:"+ Constant.BASE_PATH + filename);
        if (!resource.exists()) {
            throw new FileNotFoundException("File not found: " + filename);
        }
        return resource;
    }

    public PathList.DynamicObject getImageListUsecase(String paths) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = null;
        try {
            resources = resolver.getResources("file:" + Constant.BASE_PATH+ paths+"/**/*.dcm");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PathList path = new PathList();
        Arrays.stream(resources).forEach((resource) -> {
            try {
		System.out.println(String.valueOf(resource.getURI()).split("/")[0]);
		String[] dc = String.valueOf(resource.getURI()).split("/");
		String[] aa = Arrays.copyOf(dc,dc.length - 1);
		String dd = String.join("/",aa).split("file:")[1];
        System.out.println(dd);
                path.addPath(String.valueOf(resource.getURI()),DirList.countDirectories(dd));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return path.dymaic;
    }
    public ResponseInterface getImagePathUsecase(int start) {
        DirList dir = new DirList();
        ResponseInterface res = new ResponseInterface();
        ResponseInterface.Data data = new ResponseInterface.Data();

        res.setMessage("ดึงรายการสำเร็จ");
        try {
            DynamicObjectInterface dynamicFileCount = dir.DirGet("file:"+ Constant.BASE_PATH+"**",start);
            data.setFile(dynamicFileCount.getFile());
            data.setFindFileCount(dynamicFileCount.getCountFind());
                data.setFileCount(DirList.countDirectories(Constant.BASE_PATH));
            res.setData(data);
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
