package com.board.gd.mail;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.io.File;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by gd.godong9 on 2017. 4. 18.
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MailMessage extends SimpleMailMessage {
    private static final long serialVersionUID = 1830106734321133565L;

    @Value("${mail.from}")
    private String from;

    @Value("${mail.encoding}")
    private String encoding;

    private List<File> attachments;
    private boolean htmlContent;

    public MailMessage() {
        super();
        super.setSentDate(Calendar.getInstance().getTime());
        super.setFrom(from);
        this.attachments = Lists.newArrayList();
    }

    public MailMessage addAttachment(File file) {
        if (!Objects.isNull(file)) {
            this.attachments.add(file);
        }
        return this;
    }

    public MailMessage removeAttachment(File file) {
        if (!Objects.isNull(file) && this.attachments.contains(file)) {
            this.attachments.remove(file);
        }
        return this;
    }

    public List<File> getAttachments() {
        return Collections.unmodifiableList(this.attachments);
    }

    public boolean isMultipart() {
        return !this.attachments.isEmpty();
    }
}
