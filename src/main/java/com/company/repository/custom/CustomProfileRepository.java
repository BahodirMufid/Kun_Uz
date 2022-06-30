package com.company.repository.custom;
// PROJECT NAME Kun_Uz
// TIME 19:07
// MONTH 06
// DAY 24

import com.company.dto.ProfileFilterDTO;
import com.company.entity.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public class CustomProfileRepository {

    @Autowired
    private EntityManager entityManager;

    public List<ProfileEntity> filter(ProfileFilterDTO dto) {

        StringBuilder builder = new StringBuilder();
        builder.append(" SELECT p FROM ProfileEntity p ");
        builder.append(" where ");

        if (dto.getId() != null) {
            builder.append(" p.id = '" + dto.getId() + "' ");
        }

        if (dto.getName() != null) {
            builder.append(" and p.name = '" + dto.getName() + "' ");
        }
        if (dto.getSurname() != null) {
            builder.append(" and p.surname = '" + dto.getSurname() + "' ");
        }
        if (dto.getEmail() != null) {
            builder.append(" and p.email = '" + dto.getEmail() + "' ");
        }
        // Select a from ArticleEntity a where title = 'asdasd'; delete from sms-- fdsdfsdfs'
        if (dto.getStatus() != null) {
            builder.append(" and p.status = '" + dto.getStatus() + "' ");
        }
        if (dto.getRole() != null) {
            builder.append(" and p.role = '" + dto.getRole() + "' ");
        }
        if (dto.getCreatedDateFrom() != null && dto.getCreatedDateTo() == null) {
            // builder.append(" and p.publishDate = '" + dto.getPublishedDateFrom() + "' ");

            LocalDate localDate = LocalDate.parse(dto.getCreatedDateFrom());
            LocalDateTime fromTime = LocalDateTime.of(localDate, LocalTime.MIN); // yyyy-MM-dd 00:00:00
            LocalDateTime toTime = LocalDateTime.of(localDate, LocalTime.MAX); // yyyy-MM-dd 23:59:59

            builder.append(" and p.createdDate between '" + fromTime + "' and '" + toTime + "' ");
        } else if (dto.getCreatedDateFrom() != null && dto.getCreatedDateTo() != null) {

            builder.append(" and p.createdDate between '" + dto.getCreatedDateFrom() + "' and '" + dto.getCreatedDateTo() + "' ");

        }


//        entityManager.createQuery("select a from ProfileEntity a where a.createdDate ")

        Query query = entityManager.createQuery(builder.toString());
        List<ProfileEntity> profileList = query.getResultList();

        return profileList;
    }
}
