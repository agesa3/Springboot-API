package com.otblabs.springdevtest.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    // 1000*60*60*24*10 = 864_000_000// 10 days
    // 1000*60*5 = 300_000// 5 minutes
    // 1000*60*30 = 1_800_000// 30 minutes
    public static final long EXPIRATION_TIME = 1_800_000;// 30 minutes
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

}
