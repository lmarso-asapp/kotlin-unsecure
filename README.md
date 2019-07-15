**DevSecOps** 


**Ugly** Spring Boot -  Kotlin application used to test security scanner

**XSS:**
* http://localhost:8080/article?slug=%3Cscript%3Ealert(%22TEST%22);%3C/script%3E

**SQL injection:**
* http://localhost:8080/user?login=abc%27%20or%20%271%27=%271


**Vulnerable library:**
* xstream 1.4.2: http://x-stream.github.io/CVE-2017-7957.html
