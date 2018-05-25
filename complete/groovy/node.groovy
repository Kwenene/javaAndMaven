import com.microsoft.jenkins.containeragents.builders.*

def myCloud = new KubernetesCloudBuilder()
    .withCloudName("mycloud")
    .withAzureCredentialsId("<Azure Credentials Id>")
    .withResourceGroup("myResourceGroup")
    .withServiceName("myServiceName")
    .withAcsCredentialsId("<ACS Credentials Id>")
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
