package com.company.service;

import com.company.config.CustomUserDetails;
import com.company.dto.ProfileDTO;
import com.company.dto.ProfileFilterDTO;
import com.company.entity.AttachEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.exps.AlreadyExist;
import com.company.exps.AlreadyExistPhone;
import com.company.exps.BadRequestException;
import com.company.exps.ItemNotFoundException;
import com.company.repository.ProfileRepository;
import com.company.repository.custom.CustomProfileRepository;
import com.company.util.CurrentUser;
import org.springframework.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AttachService attachService;

    @Autowired
    private CustomProfileRepository customProfileRepository;

    public ProfileDTO create(ProfileDTO profileDto) {

        Optional<ProfileEntity> entity = profileRepository.findByEmail(profileDto.getEmail());

        if (entity.isPresent()) {
            throw new AlreadyExistPhone("Already exist phone");
        }

        if (!profileDto.getEmail().endsWith("@gmail.com")) {
            throw new BadRequestException("Email or Password is wrong");
        }

        isValid(profileDto);

        ProfileEntity profile = new ProfileEntity();
        profile.setName(profileDto.getName());
        profile.setSurname(profileDto.getSurname());
        profile.setEmail(profileDto.getEmail());
        profile.setRole(ProfileRole.USER);

//      AttachEntity attach = attachService.get(profileDto.getPhotoId());
        profile.setPhoto(attachService.get(profileDto.getPhotoId()));

        profile.setPassword(profileDto.getPassword());
        profile.setStatus(ProfileStatus.ACTIVE);

        profileRepository.save(profile);

        ProfileDTO responseDTO = new ProfileDTO();
        responseDTO.setId(profile.getId());
        responseDTO.setName(profileDto.getName());
        responseDTO.setSurname(profileDto.getSurname());
        responseDTO.setEmail(profileDto.getEmail());
        responseDTO.setRole(ProfileRole.USER);
        responseDTO.setPassword(profileDto.getPassword());
        responseDTO.setStatus(ProfileStatus.ACTIVE);
        responseDTO.setPassword(null);

        return responseDTO;
    }

    public ProfileEntity getCurrentUser(){
        CustomUserDetails currentUser = CurrentUser.getCurrentUser();
       return currentUser.getProfile();
    }

    public List<ProfileDTO> getList() {
        Iterable<ProfileEntity> all = profileRepository.findAllByVisible(true);
        List<ProfileDTO> dtoList = new LinkedList<>();
        all.forEach(profileEntity -> {
            ProfileDTO dto = new ProfileDTO();
            dto.setId(profileEntity.getId());
            dto.setName(profileEntity.getName());
            dto.setSurname(profileEntity.getSurname());
            dto.setEmail(profileEntity.getEmail());
            dtoList.add(dto);
        });
        return dtoList;
    }

    public void update(ProfileDTO dto) {

        Optional<ProfileEntity> profile = profileRepository.findById(getCurrentUser().getId());

        if (profile.isEmpty()) {
            throw new ItemNotFoundException("Profile Not Found ");
        }

        isValidUpdate(dto);

        ProfileEntity entity = profile.get();


        // 1st photo
        // bor edi yangisiga almashtiradi
        // null (yoq)

        if (entity.getPhoto() == null && dto.getPhotoId() != null) {

            entity.setPhoto(new AttachEntity(dto.getPhotoId()));

        } else if (entity.getPhoto() != null && dto.getPhotoId() == null) {

            attachService.delete(entity.getPhoto().getId());
            entity.setPhoto(null);

        } else if (entity.getPhoto() != null && dto.getPhotoId() != null &&
                entity.getPhoto().getId().equals(dto.getPhotoId())) {

            attachService.delete(entity.getPhoto().getId());
            entity.setPhoto(new AttachEntity(dto.getPhotoId()));

        }

        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        profileRepository.save(entity);

    }


    public void delete(Integer id) {
        Optional<ProfileEntity> profile = profileRepository.findById(id);
        if (profile.isEmpty()) {
            throw new ItemNotFoundException("not found profile");
        }
        if (!profile.get().getVisible()) {
            throw new AlreadyExist("IsVisible False edi");
        }

        profile.get().setVisible(Boolean.FALSE);
        profileRepository.save(profile.get());
    }

    private void isValidUpdate(ProfileDTO dto) {

        if (dto.getName() == null || dto.getName().length() < 3) {
            throw new BadRequestException("wrong name");
        }

        if (dto.getSurname() == null || dto.getSurname().length() < 4) {
            throw new BadRequestException("surname required.");
        }

        if (dto.getEmail() == null || dto.getEmail().length() < 3) {
            throw new BadRequestException("email required.");
        }


    }

    public ProfileEntity get(Integer id) {
        ProfileEntity profileEntity = profileRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Region not found");
        });
        return profileEntity;
    }

    private void isValid(ProfileDTO dto) {

        if (dto.getName() == null || dto.getName().length() < 3) {
            throw new BadRequestException("wrong name");
        }

        if (dto.getSurname() == null || dto.getSurname().length() < 4) {
            throw new BadRequestException("surname required.");
        }

        if (dto.getEmail() == null || dto.getEmail().length() < 3) {
            throw new BadRequestException("email required.");
        }

    }

    public void save(ProfileEntity moderator) {

        Optional<ProfileEntity> byEmail = profileRepository.findByEmail(moderator.getEmail());

        if (!byEmail.isPresent()) {
            profileRepository.save(moderator);
        }

    }

    public PageImpl paginationProfile(int page, int size) {

        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ProfileEntity> all = profileRepository.findAll(pageable);

//        List<ProfileEntity> list = all.getContent();

        List<ProfileDTO> dtoList = new LinkedList<>();

        all.getContent().forEach(profileEntity -> {
            ProfileDTO dto = new ProfileDTO();
            dto.setId(profileEntity.getId());
            dto.setName(profileEntity.getName());
            dto.setSurname(profileEntity.getSurname());
            dto.setEmail(profileEntity.getEmail());
            dto.setRole(profileEntity.getRole());
            dtoList.add(dto);
        });

        return new PageImpl(dtoList, pageable, all.getTotalElements());
    }

    public List<ProfileDTO> filter(ProfileFilterDTO dto) {
        List<ProfileEntity> filter = customProfileRepository.filter(dto);

        List<ProfileDTO> profileList = new LinkedList<>();

        filter.forEach(profileEntity -> {

            profileList.add(shortDTOInfo(profileEntity));
        });

        return profileList;
    }

    public ProfileDTO shortDTOInfo(ProfileEntity profile) {
        ProfileDTO dto = new ProfileDTO();

        dto.setName(profile.getName());
        dto.setSurname(profile.getSurname());
        dto.setEmail(profile.getEmail());

        dto.setStatus(profile.getStatus());
        dto.setRole(profile.getRole());
        dto.setPhotoId(profile.getPhoto().getId());
        return dto;
    }
}

