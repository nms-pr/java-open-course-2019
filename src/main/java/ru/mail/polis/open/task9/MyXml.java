package ru.mail.polis.open.task9;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.OutputStream;

@XmlRootElement(name = "rss")
@XmlType(propOrder = {"title", "description", "link", "pubDate"})
public class MyXml {
    private String title;
    private String description;
    private String link;
    private String pubDate;

    public String getTitle() {
        return title;
    }

    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    @XmlElement
    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    @XmlElement
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }


    public static void saveCustomChannelXml(OutputStream outputStream, MyXml creater) {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(MyXml.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(creater, outputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
