package tech.capedev.springboot

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val userRepository: UserRepository,
        val articleRepository: ArticleRepository) {

    @Test
    fun `When findByIdOrNull then return Article`() {
        val user = User("login", "firstname", "lastname")
        entityManager.persist(user)
        val article = Article("title", "headline", "content", user)
        entityManager.persist(article)
        entityManager.flush()
        assertNotNull(article.id)
        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `When findByLogin then return User`() {
        val initUser = User("login", "firstname", "lastname")
        entityManager.persist(initUser)
        entityManager.flush()
        val user = userRepository.findByLogin(initUser.login)
        assertThat(user).isEqualTo(initUser)
    }
}
