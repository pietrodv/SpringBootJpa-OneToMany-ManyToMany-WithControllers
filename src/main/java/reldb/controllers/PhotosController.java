package reldb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reldb.ResourceNotFoundException;
import reldb.models.Photo;
import reldb.repositories.PhotoRepository;
import reldb.repositories.UserRepository;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;


@Controller
@RequestMapping("/users")
public class PhotosController {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/{user_id}/add_photo")
    public @ResponseBody String addNewPhoto(@Valid @PathVariable (value = "user_id") Integer userId, @RequestParam String photoUUID) {
        Photo p = new Photo();

        userRepository.findById(userId).map( user -> {
            p.setUser(user);
            p.setPhotoUUID(photoUUID);
            return photoRepository.save(p);
        });
        return "Photo Saved";
    }


    @PutMapping("/{user_id}/photos/{photo_id}")
    public @ResponseBody String editPhoto(@PathVariable (value = "photo_id") Integer photoId, @Valid @PathVariable (value = "user_id") Integer userId, String photoUUID) {
        photoRepository.findByIdAndUser_Id(photoId, userId).map(photo -> {
            photo.setPhotoUUID(photoUUID);
            return photoRepository.save(photo);
        });
        return "Photo Updated";
    }

    @GetMapping("/{user_id}/photos")
    public @ResponseBody Iterable<Photo> getAllPhotos(@Valid @PathVariable (value = "user_id") Integer userId) {
        // This returns a JSON or XML with the users
        return photoRepository.findPhotoByUser_Id(userId);
    }

    @DeleteMapping("/{user_id}/photos/{photo_id}")
    // Using ResponseEntity to manage the return message
    public ResponseEntity<?> deletePhotoById(@Valid @PathVariable (value = "user_id") Integer userId, @PathVariable (value = "photo_id") Integer photoId ) {

        return photoRepository.findByIdAndUser_Id(photoId, userId).map(photo -> {
            photoRepository.delete(photo);
            return new  ResponseEntity<>("Photo with id " + photoId + " deleted", OK );
        }).orElseThrow(() -> new ResourceNotFoundException("Photo not found with id " + photoId + " and postId " + userId));
    }
}
