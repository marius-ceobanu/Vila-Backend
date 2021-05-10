package com.marius.vila.messaging.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(generator = "messageGen")
    @TableGenerator(name = "messageGen")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String message;
}
