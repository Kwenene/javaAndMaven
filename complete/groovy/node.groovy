import com.microsoft.jenkins.containeragents.builders.*

def myCloud = new KubernetesCloudBuilder()
    .withCloudName("AzureCloud")
    .withAzureCredentialsId("73620b42-8194-4319-9506-95f687811647")
    .withResourceGroup("mavenResourceGroup")
    .withServiceName("mavenProject")
    .withAcsCredentialsId("5495a590-0146-4c9f-b21f-06e3a1fdf0c0")
    .addNewTemplate()
        .withName("mytemplate")
        .withLabel("k8s")
    .endTemplate()
    .build();

Jenkins.getInstance().clouds.add(myCloud);

//inherit template from existing template
import com.microsoft.jenkins.containeragents.builders.*

def baseTemplate = new PodTemplateBuilder()
    .withImage("privateImage")
    .addNewImagePullSecret("yourSecret")
    .addNewEnvVar("key", "value")
    .build();

def myCloud = new KubernetesCloudBuilder()
    .withCloudName("mycloud")
    .withAzureCredentialsId("<Azure Credentials Id>")
    .withResourceGroup("myResourceGroup")
    .withServiceName("myServiceName")
    .withAcsCredentialsId("<ACS Credentials Id>")
    .addNewTemplateLike(baseTemplate)
        .withName("mytemplate")
        .withLabel("k8s")
    .endTemplate()
    .build();

Jenkins.getInstance().clouds.add(myCloud);
