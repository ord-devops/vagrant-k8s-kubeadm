import org.csanchez.jenkins.plugins.kubernetes.*
import jenkins.model.*
import java.io.*
import java.util.Collections


def j = Jenkins.getInstance()

def k = new KubernetesCloud(
  'kubernetes',
  null,
  'https://192.168.100.10:6443',
  'default',
  'http://192.168.100.20:8080/',
  '10', 0, 0, 5
)
k.setServerCertificate(new File('/var/lib/jenkins/kubernetes-ca.crt').text)
k.setSkipTlsVerify(false)
k.setCredentialsId('kubernetes-certkey')

def p = new PodTemplate('centos:6', Collections.emptyList())
p.setName('centos6')
p.setLabel('centos6-docker')
p.setRemoteFs('/home/jenkins')

k.addTemplate(p)

p = new PodTemplate('centos:7', Collections.emptyList())
p.setName('centos7')
p.setLabel('centos7-docker')
p.setRemoteFs('/home/jenkins')

k.addTemplate(p)

j.clouds.replace(k)
j.save()
