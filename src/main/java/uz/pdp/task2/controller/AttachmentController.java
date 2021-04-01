package uz.pdp.task2.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.task2.entity.Attachment;
import uz.pdp.task2.payload.Result;
import uz.pdp.task2.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    final AttachmentRepository attachmentRepository;

    public AttachmentController(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    private static final String uploadDirectory = "uploads";


    @PostMapping("/uploadSystem")
    public HttpEntity<String> uploadFileToFileSystem(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile files = request.getFile(fileNames.next());
        if (files != null) {
            Attachment attachment = new Attachment();
            String originalFilename = files.getOriginalFilename();
            attachment.setOriginalName(originalFilename);
            attachment.setSize(files.getSize());
            attachment.setContentType(files.getContentType());
            String[] split = originalFilename.split("\\.");
            String name = UUID.randomUUID().toString() + "." + split[split.length - 1];

            attachment.setName(name);

            Path path = Paths.get(uploadDirectory + "/" + name);
            Files.copy(files.getInputStream(), path);
            attachment.setPath(path.toString());
            attachmentRepository.save(attachment);
            return new HttpEntity<>("File saved id : " + attachment.getId());


        }
        return new HttpEntity<>("File not saved");
    }

    @GetMapping("/getFileFromSystem/{id}")
    public void getFileFromSystem(@PathVariable Integer id, HttpServletResponse response) throws IOException {

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            response.setHeader("Content-Disposition", "attachment;filename=\"" + attachment.getOriginalName() + "\"");
            response.setContentType(attachment.getContentType());
            FileInputStream fileInputStream = new FileInputStream(uploadDirectory + "/" + attachment.getName());
            FileCopyUtils.copy(fileInputStream, response.getOutputStream());
        }
    }

    @GetMapping("/byId")
    public HttpEntity<List<Attachment>> getFilesInfo() {
        List<Attachment> all = attachmentRepository.findAll();
        return new HttpEntity<>(all);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteFile(@PathVariable Integer id) {

        Result result = new Result();

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);

        if (!optionalAttachment.isPresent()) {

            result.setMessage("Such file not found");
            result.setSuccess(false);
        }
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            attachmentRepository.deleteById(id);
            String path = attachment.getPath();
            try {
                Files.deleteIfExists(Paths.get(path));
                result.setMessage("File deleted");
                result.setSuccess(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.status(result.getSuccess() ? 200 : 409).body(result);
    }
}
