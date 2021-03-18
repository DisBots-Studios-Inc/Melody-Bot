import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.31"
    id("com.eden.orchidPlugin") version "0.21.1"
}

group = "com.disbots.documentation"
version = "0.3.0a"

project.version = "0.3.0a"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    val orchidVersion = "0.21.0"
    implementation(kotlin("stdlib"))
    implementation("io.github.javaeden.orchid:OrchidCore:$orchidVersion")
    orchidImplementation("io.github.javaeden.orchid:OrchidCore:$orchidVersion")
    orchidImplementation("io.github.javaeden.orchid:OrchidPosts:$orchidVersion")
    orchidImplementation("io.github.javaeden.orchid:OrchidPages:$orchidVersion")
    orchidImplementation("io.github.javaeden.orchid:OrchidWiki:$orchidVersion")
    orchidImplementation("io.github.javaeden.orchid:OrchidNetlify:$orchidVersion")
    orchidImplementation("io.github.javaeden.orchid:OrchidNetlifyCMS:$orchidVersion")
    orchidImplementation("io.github.javaeden.orchid:OrchidPluginDocs:$orchidVersion")
    orchidImplementation("io.github.javaeden.orchid:OrchidSearch:$orchidVersion")
    orchidImplementation("io.github.javaeden.orchid:OrchidWritersBlocks:$orchidVersion")
    orchidImplementation("io.github.javaeden.orchid:OrchidSyntaxHighlighter:$orchidVersion")
    orchidImplementation("io.github.javaeden.orchid:OrchidTaxonomies:$orchidVersion")
    orchidImplementation("io.github.javaeden.orchid:OrchidEditorial:$orchidVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}