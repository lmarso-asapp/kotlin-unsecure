package tech.capedev.springboot

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import javax.persistence.EntityManager


interface ArticleRepository : CrudRepository<Article, Long> {
    fun findBySlug(slug: String): Article?
    fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}

interface UnsecureUserRepository {
    fun findByLoginUnsecure(@Param("login") login: String): User?
}

@Suppress("unused")
class UnsecureUserRepositoryImpl(private val entityManager: EntityManager) : UnsecureUserRepository {
    override fun findByLoginUnsecure(login: String): User? {
        val jql = "from User where login = '$login'"
        val q = entityManager.createQuery(jql, User::class.java)
        return q.singleResult
    }

}

interface UserRepository : CrudRepository<User, Long>, UnsecureUserRepository {
    fun findByLogin(login: String): User?
}
