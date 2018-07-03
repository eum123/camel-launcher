package net.mj.camel.launcher.web.service.router;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import net.mj.camel.launcher.web.common.serializer.DateToStringSerializer;

import java.util.Date;


public class RouteEntity {

    @Getter
    @JsonProperty("fileName")
    private String fileName;

    @Getter
    @JsonProperty("routeId")
    private String routeId;

    /** 시작시간 */
    @Getter @Setter
    @JsonProperty("startTimestamp") @JsonSerialize(using = DateToStringSerializer.class)
    private Date startTimestamp;

    /** 총 건수 */
    @Getter @Setter
    @JsonProperty("exchangesTotal")
    private long exchangesTotal;

    /** 실패건수 */
    @Getter @Setter
    @JsonProperty("exchangesFailed")
    private long exchangesFailed;

    /** 처리중 건수 */
    @Getter @Setter
    @JsonProperty("exchangesInflight")
    private long exchangesInflight;

    /** 마지막 처리 시간 */
    @Getter @Setter
    @JsonProperty("lastProcessingTime")
    private long lastProcessingTime;

    /** 최소 처리시간 */
    @Getter @Setter
    @JsonProperty("minProcessingTime")
    private long minProcessingTime;

    /** 최대 처리시간 */
    @Getter @Setter
    @JsonProperty("maxProcessingTime")
    private long maxProcessingTime;


    /** 동작시간 */
    @Getter @Setter
    @JsonProperty("uptimeMills")
    private long uptimeMills;

    /** 설명 */
    @Getter @Setter
    @JsonProperty("description")
    private String description;

    /** 상태 */
    @Getter @Setter
    @JsonProperty("state")
    private String state;

    public RouteEntity(String fileName, String routeId) {
        this.fileName = fileName;
        this.routeId = routeId;
    }

}
