FROM quay.io/keycloak/keycloak:21.1
ENV PROXY_ADDRESS_FORWARDING=true
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start --proxy=edge","--auto-build","--hostname-strict=false", "--db=postgres", "-Dquarkus.http.host=0.0.0.0"]
RUN /opt/keycloak/bin/kc.sh build