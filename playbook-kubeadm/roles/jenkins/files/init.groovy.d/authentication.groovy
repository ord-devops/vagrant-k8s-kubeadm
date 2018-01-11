import jenkins.*
import hudson.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import hudson.plugins.sshslaves.*;
import hudson.model.*
import jenkins.model.*
import hudson.security.*

def instance = Jenkins.getInstance()

// NOW TIME TO CONFIGURE GLOBAL SECURITY
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
//  sample LDAP setup
String server = 'ldap://ipa.lab.example.com'
String rootDN = 'dc=lab,dc=example,dc=com'
String userSearchBase = 'cn=users,cn=accounts'
String userSearch = 'uid={0}'
String groupSearchBase = 'cn=groups,cn=accounts'
String managerDN = 'uid=srvc_jenkins,cn=users,cn=accounts,dc=lab,dc=example,dc=com'
String passcode = 'Jenkins-123'
boolean inhibitInferRootDN = true
SecurityRealm ldap_realm = new LDAPSecurityRealm(server, rootDN, userSearchBase, userSearch, groupSearchBase, managerDN, passcode, inhibitInferRootDN) 
instance.setSecurityRealm(ldap_realm)
instance.save()

def strategy = new GlobalMatrixAuthorizationStrategy()
strategy.add(Jenkins.ADMINISTER, "admin")
instance.setAuthorizationStrategy(strategy)
instance.save()
