package BuildWeekU5.EPIC.ENERGY.SERVICE.controllers;


import BuildWeekU5.EPIC.ENERGY.SERVICE.Entities.Utente;
import BuildWeekU5.EPIC.ENERGY.SERVICE.Services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;


    @GetMapping
    public Page<Utente> getAllUtenti(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return this.utenteService.getAllUtenti(page, size, sortBy);
    }

    @GetMapping("/{utenteId}")
    public Utente findById(@PathVariable Long utenteId) {
        return this.utenteService.findById(utenteId);
    }

    @GetMapping("/me")
    public Utente getProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser) {
        this.utenteService.findByIdAndDelete(currentAuthenticatedUser.getId());

    }

    @PostMapping("/me/avatar")
    public Utente uploadAvatar(@RequestParam("avatar") MultipartFile image, @AuthenticationPrincipal Utente utenteId) throws IOException {
        return this.utenteService.uploadAvatar(utenteId.getId(), image);
    }
@DeleteMapping("/{utenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteProfile(@PathVariable Long utenteId){
    this.utenteService.findByIdAndDelete(utenteId);
}


}