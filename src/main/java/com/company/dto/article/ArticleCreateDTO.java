package com.company.dto.article;

import com.company.enums.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Getter
@Setter
public class ArticleCreateDTO {
    private String uuid;
    @NotNull(message = "Mazgi title xato (is null)")
    @Size(min = 5, max = 255, message = "Title must be between 5 and 255 characters")
    private String title;
    @Size(min = 10, max = 255, message = "Content must be between 10 and 255 characters")
    @NotNull(message = "Mazgi content xato")
    private String content;
    @NotEmpty(message = "Description is not Valid")
    private String description;
    private Integer regionId;
    private Integer categoryId;
    private List<Integer> typesList;
    private List<String> tagList;
    private ArticleStatus status;
    private String imageId;

}
