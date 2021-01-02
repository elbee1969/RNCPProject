package fr.formation.eprint.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.formation.eprint.config.SecurityHelper;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.Image3D;
import fr.formation.eprint.repositories.NewUserJpaRepository;
import fr.formation.eprint.response.MessageImage3DResponse;
import fr.formation.eprint.services.FileStorageService;

@RestController
@RequestMapping("/private") // "/api/private/*"
public class FilesController {

  @Autowired
  FileStorageService storageService;
  
  @Autowired
  private NewUserJpaRepository userRepository;

  @PostMapping("/upload")
  public ResponseEntity<MessageImage3DResponse> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
    String message = "";

      storageService.save(file);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(new MessageImage3DResponse(message));
   
  }

  @GetMapping("/files")
  public ResponseEntity<List<Image3D>> getListFiles() {
    List<Image3D> fileInfos = storageService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
      Long userId = SecurityHelper.getUserId();
	    CustomUser customUser = userRepository.getOne(userId);
      return new Image3D(filename, url, customUser);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }

  @GetMapping("/files/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
}