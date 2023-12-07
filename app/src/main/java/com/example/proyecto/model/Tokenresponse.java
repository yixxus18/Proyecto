// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.proyecto.model;
import java.util.List;

public class Tokenresponse {
    private String accessToken;
    private String tokenType;
    private long expiresIn;

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String value) { this.accessToken = value; }

    public String getTokenType() { return tokenType; }
    public void setTokenType(String value) { this.tokenType = value; }

    public long getExpiresIn() { return expiresIn; }
    public void setExpiresIn(long value) { this.expiresIn = value; }
}
