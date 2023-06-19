package br.com.clusterlab.kafkaconfluentreplicatororchestrator.dto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import jakarta.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "action",
        "source_cluster",
        "servers",
        "topics"
})
@Generated("jsonschema2pojo")
public class TopicsDTO {

    @JsonProperty("action")
    private String action;
    @JsonProperty("source_cluster")
    private String sourceCluster;
    @JsonProperty("servers")
    private List<String> servers;
    @JsonProperty("topics")
    private List<String> topics;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("source_cluster")
    public String getSourceCluster() {
        return sourceCluster;
    }

    @JsonProperty("source_cluster")
    public void setSourceCluster(String sourceCluster) {
        this.sourceCluster = sourceCluster;
    }

    @JsonProperty("servers")
    public List<String> getServers() {
        return servers;
    }

    @JsonProperty("servers")
    public void setServers(List<String> servers) {
        this.servers = servers;
    }

    @JsonProperty("topics")
    public List<String> getTopics() {
        return topics;
    }

    @JsonProperty("topics")
    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}