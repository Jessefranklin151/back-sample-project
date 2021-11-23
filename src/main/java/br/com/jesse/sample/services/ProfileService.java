package br.com.jesse.sample.services;

import org.springframework.stereotype.Service;

import br.com.jesse.sample.models.Profile;
import br.com.jesse.sample.repositories.ProfileRepository;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile createProfileIfNotExists(Profile p) {
        Profile profile = profileRepository.findProfileByName(p.getName());

        if (profile == null) {
            profile = profileRepository.save(p);
        }

        return profile;
    }
}
