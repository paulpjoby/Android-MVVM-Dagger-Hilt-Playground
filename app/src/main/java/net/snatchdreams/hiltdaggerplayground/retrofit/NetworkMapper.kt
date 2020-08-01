package net.snatchdreams.hiltdaggerplayground.retrofit

import net.snatchdreams.hiltdaggerplayground.model.Blog
import net.snatchdreams.hiltdaggerplayground.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
    @Inject
    constructor(): EntityMapper<BlogNetworkEntity, Blog> {
    override fun mapFromEntity(entity: BlogNetworkEntity): Blog {
        return Blog(
            id = entity.id,
            body = entity.body,
            title = entity.title,
            category = entity.category,
            image = entity.image
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            category = domainModel.category,
            image = domainModel.image,
            body = domainModel.body
        )
    }

    fun mapFromEntityList(entities: List<BlogNetworkEntity>): List<Blog>
    {
        return entities.map { mapFromEntity(it) };
    }
}