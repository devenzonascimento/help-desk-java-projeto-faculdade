package project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.DTOS.ProfileDTO;
import project.Entities.Profile;
import project.Services.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @PostMapping("/{profile}")
    public @ResponseBody ResponseEntity<ProfileDTO> create(@PathVariable String profile) {
        Profile createdProfile = profileService.create(profile);

        return ResponseEntity.ok().body(ProfileDTO.valueOf(createdProfile));
    }

}
