import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.plugins.credentials.impl.*;

String keyfile="/var/lib/jenkins/kubernetes.pfx"
String password="jenkins"

def ksm1 = new CertificateCredentialsImpl.FileOnMasterKeyStoreSource(keyfile)
Credentials ck1 = new CertificateCredentialsImpl(CredentialsScope.GLOBAL,"kubernetes-certkey", "CN=Jenkins", password, ksm1)
SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), ck1)

