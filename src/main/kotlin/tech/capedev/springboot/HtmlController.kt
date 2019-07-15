package tech.capedev.springboot

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class HtmlController(private val articleRepository: ArticleRepository, private val userRepository: UserRepository) {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "[Springboot- Kotlin] Articles with security issues"
        model["banner"] = "Articles"
        model["articles"] = articleRepository.findAllByOrderByAddedAtDesc().map { it.render() }
        return "blog"
    }

    @GetMapping("/user")
    fun user(@RequestParam("login") login: String, model: Model): String {
        val user = userRepository
                .findByLoginUnsecure(login)
                ?: throw IllegalArgumentException("Wrong user login provided: $login")
        model["title"] = login
        model["user"] = user
        return "user"
    }

    @GetMapping("/article")
    fun article(@RequestParam("slug") slug: String, model: Model): String {
        val article = articleRepository
                .findBySlug(slug)
                ?.render()
                ?: return articleNotFound(slug, model)
        model["title"] = article.title
        model["article"] = article
        return "article"
    }

    fun articleNotFound(slug: String, model: Model): String {
        model["slug"] = slug
        model["title"] = "Article not found"
        return "article-not-found"
    }

    fun Article.render() = RenderedArticle(
            slug,
            title,
            headline,
            content,
            author,
            addedAt.format()
    )

    data class RenderedArticle(
            val slug: String,
            val title: String,
            val headline: String,
            val content: String,
            val author: User,
            val addedAt: String)

}
