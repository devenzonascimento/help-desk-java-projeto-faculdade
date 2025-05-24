package project.Controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.DTOS.Profile.ProfileDTO;
import project.Entities.Profile;
import project.Services.ProfileService;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @PostMapping("/{profile}")
    public @ResponseBody ResponseEntity<ProfileDTO> create(@PathVariable String profile) {
        Profile createdProfile = profileService.create(profile);

        return ResponseEntity.status(HttpStatus.CREATED).body(ProfileDTO.fromProfile(createdProfile));
    }

    @GetMapping("/{profileId}")
    public @ResponseBody ResponseEntity<ProfileDTO> findById(@PathVariable Long profileId) {
        Profile profile = profileService.findById(profileId);

        if (profile == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(ProfileDTO.fromProfile(profile));
    }

    @GetMapping("")
    public @ResponseBody ResponseEntity<List<ProfileDTO>> findAll() {
        List<Profile> profiles = profileService.findAll();

        return ResponseEntity.ok(ProfileDTO.fromProfiles(profiles));
    }
}
