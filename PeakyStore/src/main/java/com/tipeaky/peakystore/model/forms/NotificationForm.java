package com.tipeaky.peakystore.model.forms;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationForm {
    @NotNull
    private Boolean notification;
}
