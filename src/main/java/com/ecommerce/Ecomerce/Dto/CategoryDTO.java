package com.ecommerce.Ecomerce.Dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ecommerce.Ecomerce.Entity.Category;
import lombok.Data;

@Data
public class CategoryDTO {
    private UUID id;
    private UUID parentId;
    private String categoryName;
    private String description;
    private String icon;
    private String image;
    private String placeholder;
    private boolean active;
    private Date createdAt;
    private Date updatedAt;
    private List<CategoryDTO> children;

    // getters và setters omitted for brevity

    // Phương thức chuyển từ Entity sang DTO (bao gồm đệ quy với children)
    public static CategoryDTO fromEntity(Category entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setParentId(entity.getParent() != null ? entity.getParent().getId() : null);
        dto.setCategoryName(entity.getCategoryName());
        dto.setDescription(entity.getDescription());
        dto.setIcon(entity.getIcon());
        dto.setImage(entity.getImage());
        dto.setPlaceholder(entity.getPlaceholder());
        dto.setActive(entity.isActive());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        // Map children nếu có
        if (entity.getChildren() != null && !entity.getChildren().isEmpty()) {
            dto.setChildren(
                    entity.getChildren().stream()
                            .map(CategoryDTO::fromEntity)
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }
}
