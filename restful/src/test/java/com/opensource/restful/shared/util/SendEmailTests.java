package com.opensource.restful.shared.util;

import static org.junit.Assert.assertEquals;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations =
{ "classpath*:applicationContext.xml" })
public class SendEmailTests
{
    private final static Log logger = LogFactory.getLog(SendEmailTests.class);

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private ISendEmailService service;

    @Test
    public void testSendEmail()
    {

        // MailMail mm = (MailMail) context.getBean("mailMail");

        service.sendMail("duncan20s@msn.com", "tom@tomholmes.net", "Testing123",
            "Testing only \n\n Hello Spring Email Sender");

        assertEquals(true, true);
    }
}

/*
org.springframework.mail.MailSendException: Mail server connection failed; nested exception is javax.mail.MessagingException: Could not convert socket to TLS;
nested exception is:
  javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target. Failed messages: javax.mail.MessagingException: Could not convert socket to TLS;
nested exception is:
  javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target; message exception details (1) are:
Failed message 1:
javax.mail.MessagingException: Could not convert socket to TLS;
nested exception is:
  javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
  at com.sun.mail.smtp.SMTPTransport.startTLS(SMTPTransport.java:1907)
  at com.sun.mail.smtp.SMTPTransport.protocolConnect(SMTPTransport.java:666)
  at javax.mail.Service.connect(Service.java:295)
  at org.springframework.mail.javamail.JavaMailSenderImpl.doSend(JavaMailSenderImpl.java:389)
  at org.springframework.mail.javamail.JavaMailSenderImpl.send(JavaMailSenderImpl.java:306)
  at org.springframework.mail.javamail.JavaMailSenderImpl.send(JavaMailSenderImpl.java:296)
  at com.opensource.restful.shared.util.SendEmailService.sendMail(SendEmailService.java:31)
  at com.opensource.restful.shared.util.SendEmailTests.testSendEmail(SendEmailTests.java:35)
  at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
  at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
  at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
  at java.lang.reflect.Method.invoke(Method.java:597)
  at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:44)
  at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:15)
  at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:41)
  at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:20)
  at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:74)
  at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:83)
  at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:72)
  at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:231)
  at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:88)
  at org.junit.runners.ParentRunner$3.run(ParentRunner.java:193)
  at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:52)
  at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:191)
  at org.junit.runners.ParentRunner.access$000(ParentRunner.java:42)
  at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:184)
  at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
  at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:71)
  at org.junit.runners.ParentRunner.run(ParentRunner.java:236)
  at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:174)
  at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:50)
  at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
  at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:467)
  at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:683)
  at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:390)
  at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:197)
Caused by: javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
  at com.sun.net.ssl.internal.ssl.Alerts.getSSLException(Alerts.java:174)
  at com.sun.net.ssl.internal.ssl.SSLSocketImpl.fatal(SSLSocketImpl.java:1762)
  at com.sun.net.ssl.internal.ssl.Handshaker.fatalSE(Handshaker.java:241)
  at com.sun.net.ssl.internal.ssl.Handshaker.fatalSE(Handshaker.java:235)
  at com.sun.net.ssl.internal.ssl.ClientHandshaker.serverCertificate(ClientHandshaker.java:1206)
  at com.sun.net.ssl.internal.ssl.ClientHandshaker.processMessage(ClientHandshaker.java:136)
  at com.sun.net.ssl.internal.ssl.Handshaker.processLoop(Handshaker.java:593)
  at com.sun.net.ssl.internal.ssl.Handshaker.process_record(Handshaker.java:529)
  at com.sun.net.ssl.internal.ssl.SSLSocketImpl.readRecord(SSLSocketImpl.java:958)
  at com.sun.net.ssl.internal.ssl.SSLSocketImpl.performInitialHandshake(SSLSocketImpl.java:1203)
  at com.sun.net.ssl.internal.ssl.SSLSocketImpl.startHandshake(SSLSocketImpl.java:1230)
  at com.sun.net.ssl.internal.ssl.SSLSocketImpl.startHandshake(SSLSocketImpl.java:1214)
  at com.sun.mail.util.SocketFetcher.configureSSLSocket(SocketFetcher.java:549)
  at com.sun.mail.util.SocketFetcher.startTLS(SocketFetcher.java:486)
  at com.sun.mail.smtp.SMTPTransport.startTLS(SMTPTransport.java:1902)
  ... 35 more
Caused by: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
  at sun.security.validator.PKIXValidator.doBuild(PKIXValidator.java:323)
  at sun.security.validator.PKIXValidator.engineValidate(PKIXValidator.java:217)
  at sun.security.validator.Validator.validate(Validator.java:218)
  at com.sun.net.ssl.internal.ssl.X509TrustManagerImpl.validate(X509TrustManagerImpl.java:126)
  at com.sun.net.ssl.internal.ssl.X509TrustManagerImpl.checkServerTrusted(X509TrustManagerImpl.java:209)
  at com.sun.net.ssl.internal.ssl.X509TrustManagerImpl.checkServerTrusted(X509TrustManagerImpl.java:249)
  at com.sun.net.ssl.internal.ssl.ClientHandshaker.serverCertificate(ClientHandshaker.java:1185)
  ... 45 more
Caused by: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
  at sun.security.provider.certpath.SunCertPathBuilder.engineBuild(SunCertPathBuilder.java:174)
  at java.security.cert.CertPathBuilder.build(CertPathBuilder.java:238)
  at sun.security.validator.PKIXValidator.doBuild(PKIXValidator.java:318)
  ... 51 more

*/