package com.ZHL.TaTa.dto;

import com.ZHL.TaTa.entity.PetSale;
import lombok.Data;
import org.apache.tomcat.websocket.WsSession;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

@Data
public class PetDto extends PetSale {

    private String categoryName;

    private Integer copies;

}
