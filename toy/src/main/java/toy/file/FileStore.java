package toy.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStore {
    @Value("${file.dir}")
    String fileDir;

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    private String createUUID(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    public String fileUpload(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()) return null;
        String filename=multipartFile.getOriginalFilename();
        String uuid=createUUID(filename);
        multipartFile.transferTo(new File(fileDir+uuid));
        return uuid;
    }
}
