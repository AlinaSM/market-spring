package com.asm.market.persistence.mapper;

import com.asm.market.domain.CategoryDomain;
import com.asm.market.persistence.entity.Category;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
// Se diseñan los mappers
public interface CategoryMapper {

    @Mappings({
            @Mapping(source = "id", target = "categoryId"), //id es de la entidad (entity) y el categoryId es del dominio (domain)
            @Mapping(source = "description", target = "category"),
            @Mapping(source = "status", target = "active"),
    })

    CategoryDomain toCategoryDomain(Category category);

    @InheritInverseConfiguration //Se define que es el mapeo inverso del mapeo anterrior, con ello evitamos definir denuevo los Mappings
    @Mapping(target = "products", ignore = true) //Ignora el campo productos(entity) que se esta omitiendo en el dominio (domain)
    Category toCategory(CategoryDomain category);

}
