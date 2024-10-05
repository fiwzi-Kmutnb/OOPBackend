package net.fiwzi.oop.utils;

import net.fiwzi.oop.infrastructure.Constant;
//import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class PathList {
    public static class DynamicObject extends HashMap<String, Object> {}
    public DynamicObject dymaic = new DynamicObject();
    private void addPath(DynamicObject obj, List<String> path,int countPath) {
        for (int i = 0; i < path.size() - 2; i++) {
            String current = path.get(i);
            obj.computeIfAbsent(current, k -> new DynamicObject());
            obj = (DynamicObject) obj.get(current);
        }
        String parentDir = path.get(path.size() - 2);
        String fileName = path.get(path.size() - 1);
	if(countPath > 1) {
        obj.computeIfAbsent(parentDir, k -> new DynamicObject());
        obj = (DynamicObject) obj.get(parentDir);
		obj.computeIfAbsent("internal", k -> new ArrayList<String>());
        List<String> files = (List<String>) obj.get("internal");
        if (!files.contains(fileName)) {
            files.add(fileName);
        }
	} else {
        obj.computeIfAbsent(parentDir, k -> new ArrayList<String>());
        List<String> files = (List<String>) obj.get(parentDir);
        if (!files.contains(fileName)) {
            files.add(fileName);
        	}
	} 
    }
    public void addPath(String path,int countPath) {
        String[] splitPath = path.split(Constant.BASE_PATH);
        if (splitPath.length < 2) {
            return ;
        }
        String[] pathParts = splitPath[1].split("/");
        this.addPath(dymaic, Arrays.asList(pathParts),countPath);
    }
}
