package com.iteknical.fusion.user.constant;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ImmutableSet;

/**
 * @author czy@win10
 * @date 2020/1/20 22:26
 */
public class SitesConstant {
    /** 奥丁主站 */
    public static final String       WEDNESDAY = "wednesday";
    /** sweeney客户端 */
    public static final String       SWEENEY   = "sweeney";

    private static final Set<String> ALL_SITES = ImmutableSet.of(WEDNESDAY, SWEENEY);

    public static boolean isLegal(String site) {
        if (StringUtils.isBlank(site)) {
            return false;
        }

        return ALL_SITES.contains(site);
    }

    public static boolean hasSite(String sites, String targetSite) {
        Set<String> siteSet = JSON.parseObject(sites, new TypeReference<Set<String>>() {});
        return siteSet.contains(targetSite);
    }
}
