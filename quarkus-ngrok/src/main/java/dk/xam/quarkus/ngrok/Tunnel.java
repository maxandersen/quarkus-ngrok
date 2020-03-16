package dk.xam.quarkus.ngrok;

import java.util.Map;

public class Tunnel {
        
        private String name;
        
        private String uri;
        
        private String publicUrl;
        
        private String proto;
        
        private TunnelConfig config;
        
        private Map<String, Map<String, Integer>> metrics;

        public String getName() {
            return name;
        }

        
        public void setName(String name) {
            this.name = name;
        }

        
        public String getUri() {
            return uri;
        }

        
        public void setUri(String uri) {
            this.uri = uri;
        }

        
        public String getPublicUrl() {
            return publicUrl;
        }

        
        public void setPublicUrl(String publicUrl) {
            this.publicUrl = publicUrl;
        }

        
        public String getProto() {
            return proto;
        }

        
        public void setProto(String proto) {
            this.proto = proto;
        }

        
        public TunnelConfig getConfig() {
            return config;
        }

        
        public void setConfig(TunnelConfig config) {
            this.config = config;
        }

        
        public Map<String, Map<String, Integer>> getMetrics() {
            return metrics;
        }

        
        public void setMetrics(TunnelMetrics metrics) {
            this.metrics = metrics;
        }
}
