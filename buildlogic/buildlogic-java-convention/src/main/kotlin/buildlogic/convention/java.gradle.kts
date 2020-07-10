package buildlogic.convention

plugins {
    id("buildlogic.versioning")
    `java-library`
    `java-test-fixtures`
    id("buildlogic.libraries")
    id("buildlogic.convention.unit-testing")
    id("buildlogic.convention.end2end-testing")
    id("buildlogic.convention.jacoco")
}

dependencies {
    findProject(":platform")?.let { implementation(platform(it)) }
}

// Share sources folder with other projects for aggregated Javadoc and JaCoCo reports
jvm {
    createOutgoingElements("transitiveSourcesElements") {
        providesAttributes {
            documentation("source-folders")
        }
        extendsFrom(configurations.implementation)
        sourceSets.main.get().java.srcDirs.forEach(::artifact)
    }
}