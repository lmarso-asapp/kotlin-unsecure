package tech.capedev.springboot

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            articleRepository: ArticleRepository) = ApplicationRunner {

        val user = userRepository.save(User("user_login", "Foo", "Bar"))
        articleRepository.save(Article(
                title = "First article",
                headline = "Lorem ipsum",
                content = "dolor sit amet",
                author = user
        ))
        articleRepository.save(Article(
                title = "Second article",
                headline = "Lorem ipsum",
                content = "dolor sit amet",
                author = user
        ))
    }
}
