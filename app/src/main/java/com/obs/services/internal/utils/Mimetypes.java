package com.obs.services.internal.utils;

import com.obs.log.ILogger;
import com.obs.log.LoggerBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Mimetypes {
    public static final String MIMETYPE_BINARY_OCTET_STREAM = "binary/octet-stream";
    public static final String MIMETYPE_GZIP = "application/x-gzip";
    public static final String MIMETYPE_HTML = "text/html";
    public static final String MIMETYPE_JSON = "application/json";
    public static final String MIMETYPE_OCTET_STREAM = "application/octet-stream";
    public static final String MIMETYPE_TEXT_PLAIN = "text/plain";
    public static final String MIMETYPE_TEXT_XML = "text/xml";
    public static final String MIMETYPE_XML = "application/xml";
    private static final ILogger log = LoggerBuilder.getLogger((Class<?>) Mimetypes.class);
    private final Map<String, String> extensionToMimetypeMap;

    private static class MimetypesHolder {
        private static Mimetypes mimetypes;

        static {
            Mimetypes mimetypes2 = new Mimetypes();
            mimetypes = mimetypes2;
            InputStream resourceAsStream = mimetypes2.getClass().getResourceAsStream("/mime.types");
            if (resourceAsStream != null) {
                if (Mimetypes.log.isDebugEnabled()) {
                    Mimetypes.log.debug((CharSequence) "Loading mime types from file in the classpath: mime.types");
                }
                try {
                    mimetypes.loadAndReplaceMimetypes(resourceAsStream);
                } catch (IOException e) {
                    if (Mimetypes.log.isErrorEnabled()) {
                        Mimetypes.log.error("Failed to load mime types from file in the classpath: mime.types", e);
                    }
                }
            }
        }

        private MimetypesHolder() {
        }
    }

    private Mimetypes() {
        HashMap map = new HashMap();
        this.extensionToMimetypeMap = map;
        map.put("001", "application/x-001");
        this.extensionToMimetypeMap.put("301", "application/x-301");
        this.extensionToMimetypeMap.put("323", "text/h323");
        this.extensionToMimetypeMap.put("7z", "application/x-7z-compressed");
        this.extensionToMimetypeMap.put("906", "application/x-906");
        this.extensionToMimetypeMap.put("907", "drawing/907");
        this.extensionToMimetypeMap.put("IVF", "video/x-ivf");
        this.extensionToMimetypeMap.put("a11", "application/x-a11");
        this.extensionToMimetypeMap.put("aac", "audio/x-aac");
        this.extensionToMimetypeMap.put("acp", "audio/x-mei-aac");
        this.extensionToMimetypeMap.put("ai", "application/postscript");
        this.extensionToMimetypeMap.put("aif", "audio/aiff");
        this.extensionToMimetypeMap.put("aifc", "audio/aiff");
        this.extensionToMimetypeMap.put("aiff", "audio/aiff");
        this.extensionToMimetypeMap.put("anv", "application/x-anv");
        this.extensionToMimetypeMap.put("apk", "application/vnd.android.package-archive");
        this.extensionToMimetypeMap.put("asa", "text/asa");
        this.extensionToMimetypeMap.put("asf", "video/x-ms-asf");
        this.extensionToMimetypeMap.put("asp", "text/asp");
        this.extensionToMimetypeMap.put("asx", "video/x-ms-asf");
        this.extensionToMimetypeMap.put("atom", "application/atom+xml");
        this.extensionToMimetypeMap.put("au", "audio/basic");
        this.extensionToMimetypeMap.put("avi", "video/avi");
        this.extensionToMimetypeMap.put("awf", "application/vnd.adobe.workflow");
        this.extensionToMimetypeMap.put("biz", "text/xml");
        this.extensionToMimetypeMap.put("bmp", "application/x-bmp");
        this.extensionToMimetypeMap.put("bot", "application/x-bot");
        this.extensionToMimetypeMap.put("bz2", "application/x-bzip2");
        this.extensionToMimetypeMap.put("c4t", "application/x-c4t");
        this.extensionToMimetypeMap.put("c90", "application/x-c90");
        this.extensionToMimetypeMap.put("cal", "application/x-cals");
        this.extensionToMimetypeMap.put("cat", "application/vnd.ms-pki.seccat");
        this.extensionToMimetypeMap.put("cdf", "application/x-netcdf");
        this.extensionToMimetypeMap.put("cdr", "application/x-cdr");
        this.extensionToMimetypeMap.put("cel", "application/x-cel");
        this.extensionToMimetypeMap.put("cer", "application/x-x509-ca-cert");
        this.extensionToMimetypeMap.put("cg4", "application/x-g4");
        this.extensionToMimetypeMap.put("cgm", "application/x-cgm");
        this.extensionToMimetypeMap.put("cit", "application/x-cit");
        this.extensionToMimetypeMap.put("class", "java/*");
        this.extensionToMimetypeMap.put("cml", "text/xml");
        this.extensionToMimetypeMap.put("cmp", "application/x-cmp");
        this.extensionToMimetypeMap.put("cmx", "application/x-cmx");
        this.extensionToMimetypeMap.put("cot", "application/x-cot");
        this.extensionToMimetypeMap.put("crl", "application/pkix-crl");
        this.extensionToMimetypeMap.put("crt", "application/x-x509-ca-cert");
        this.extensionToMimetypeMap.put("csi", "application/x-csi");
        this.extensionToMimetypeMap.put("css", "text/css");
        this.extensionToMimetypeMap.put("csv", "text/csv");
        this.extensionToMimetypeMap.put("cu", "application/cu-seeme");
        this.extensionToMimetypeMap.put("cut", "application/x-cut");
        this.extensionToMimetypeMap.put("dbf", "application/x-dbf");
        this.extensionToMimetypeMap.put("dbm", "application/x-dbm");
        this.extensionToMimetypeMap.put("dbx", "application/x-dbx");
        this.extensionToMimetypeMap.put("dcd", "text/xml");
        this.extensionToMimetypeMap.put("dcx", "application/x-dcx");
        this.extensionToMimetypeMap.put("deb", "application/x-debian-package");
        this.extensionToMimetypeMap.put("der", "application/x-x509-ca-cert");
        this.extensionToMimetypeMap.put("dgn", "application/x-dgn");
        this.extensionToMimetypeMap.put("dib", "application/x-dib");
        this.extensionToMimetypeMap.put("dll", "application/x-msdownload");
        this.extensionToMimetypeMap.put("doc", "application/msword");
        this.extensionToMimetypeMap.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        this.extensionToMimetypeMap.put("dot", "application/msword");
        this.extensionToMimetypeMap.put("drw", "application/x-drw");
        this.extensionToMimetypeMap.put("dtd", "text/xml");
        this.extensionToMimetypeMap.put("dvi", "application/x-dvi");
        this.extensionToMimetypeMap.put("dwf", "application/x-dwf");
        this.extensionToMimetypeMap.put("dwg", "application/x-dwg");
        this.extensionToMimetypeMap.put("dxb", "application/x-dxb");
        this.extensionToMimetypeMap.put("dxf", "application/x-dxf");
        this.extensionToMimetypeMap.put("edn", "application/vnd.adobe.edn");
        this.extensionToMimetypeMap.put("emf", "application/x-emf");
        this.extensionToMimetypeMap.put("eml", "message/rfc822");
        this.extensionToMimetypeMap.put("ent", "text/xml");
        this.extensionToMimetypeMap.put("eot", "application/vnd.ms-fontobject");
        this.extensionToMimetypeMap.put("epi", "application/x-epi");
        this.extensionToMimetypeMap.put("eps", "application/postscript");
        this.extensionToMimetypeMap.put("epub", "application/epub+zip");
        this.extensionToMimetypeMap.put("etd", "application/x-ebx");
        this.extensionToMimetypeMap.put("etx", "text/x-setext");
        this.extensionToMimetypeMap.put("exe", "application/x-msdownload");
        this.extensionToMimetypeMap.put("fax", "image/fax");
        this.extensionToMimetypeMap.put("fdf", "application/vnd.fdf");
        this.extensionToMimetypeMap.put("fif", "application/fractals");
        this.extensionToMimetypeMap.put("flac", "audio/flac");
        this.extensionToMimetypeMap.put("flv", "video/x-flv");
        this.extensionToMimetypeMap.put("fo", "text/xml");
        this.extensionToMimetypeMap.put("frm", "application/x-frm");
        this.extensionToMimetypeMap.put("g4", "application/x-g4");
        this.extensionToMimetypeMap.put("gbr", "application/x-gbr");
        this.extensionToMimetypeMap.put("gif", "image/gif");
        this.extensionToMimetypeMap.put("gl2", "application/x-gl2");
        this.extensionToMimetypeMap.put("gp4", "application/x-gp4");
        this.extensionToMimetypeMap.put("gz", "application/gzip");
        this.extensionToMimetypeMap.put("hgl", "application/x-hgl");
        this.extensionToMimetypeMap.put("hmr", "application/x-hmr");
        this.extensionToMimetypeMap.put("hpg", "application/x-hpgl");
        this.extensionToMimetypeMap.put("hpl", "application/x-hpl");
        this.extensionToMimetypeMap.put("hqx", "application/mac-binhex40");
        this.extensionToMimetypeMap.put("hrf", "application/x-hrf");
        this.extensionToMimetypeMap.put("hta", "application/hta");
        this.extensionToMimetypeMap.put("htc", "text/x-component");
        this.extensionToMimetypeMap.put("htm", "text/html");
        this.extensionToMimetypeMap.put("html", "text/html");
        this.extensionToMimetypeMap.put("htt", "text/webviewhtml");
        this.extensionToMimetypeMap.put("htx", "text/html");
        this.extensionToMimetypeMap.put("icb", "application/x-icb");
        this.extensionToMimetypeMap.put("ico", "application/x-ico");
        this.extensionToMimetypeMap.put("ics", "text/calendar");
        this.extensionToMimetypeMap.put("iff", "application/x-iff");
        this.extensionToMimetypeMap.put("ig4", "application/x-g4");
        this.extensionToMimetypeMap.put("igs", "application/x-igs");
        this.extensionToMimetypeMap.put("iii", "application/x-iphone");
        this.extensionToMimetypeMap.put("img", "application/x-img");
        this.extensionToMimetypeMap.put("ini", "text/plain");
        this.extensionToMimetypeMap.put("ins", "application/x-internet-signup");
        this.extensionToMimetypeMap.put("ipa", "application/vnd.iphone");
        this.extensionToMimetypeMap.put("iso", "application/x-iso9660-image");
        this.extensionToMimetypeMap.put("isp", "application/x-internet-signup");
        this.extensionToMimetypeMap.put("jar", "application/java-archive");
        this.extensionToMimetypeMap.put("java", "java/*");
        this.extensionToMimetypeMap.put("jfif", "image/jpeg");
        this.extensionToMimetypeMap.put("jpe", "image/jpeg");
        this.extensionToMimetypeMap.put("jpeg", "image/jpeg");
        this.extensionToMimetypeMap.put("jpg", "image/jpeg");
        this.extensionToMimetypeMap.put("js", "application/x-javascript");
        this.extensionToMimetypeMap.put("json", "application/json");
        this.extensionToMimetypeMap.put("jsp", "text/html");
        this.extensionToMimetypeMap.put("la1", "audio/x-liquid-file");
        this.extensionToMimetypeMap.put("lar", "application/x-laplayer-reg");
        this.extensionToMimetypeMap.put("latex", "application/x-latex");
        this.extensionToMimetypeMap.put("lavs", "audio/x-liquid-secure");
        this.extensionToMimetypeMap.put("lbm", "application/x-lbm");
        this.extensionToMimetypeMap.put("lmsff", "audio/x-la-lms");
        this.extensionToMimetypeMap.put("log", "text/plain");
        this.extensionToMimetypeMap.put("ls", "application/x-javascript");
        this.extensionToMimetypeMap.put("ltr", "application/x-ltr");
        this.extensionToMimetypeMap.put("m1v", "video/x-mpeg");
        this.extensionToMimetypeMap.put("m2v", "video/x-mpeg");
        this.extensionToMimetypeMap.put("m3u", "audio/mpegurl");
        this.extensionToMimetypeMap.put("m4a", "audio/mp4");
        this.extensionToMimetypeMap.put("m4e", "video/mpeg4");
        this.extensionToMimetypeMap.put("m4v", "video/mp4");
        this.extensionToMimetypeMap.put("mac", "application/x-mac");
        this.extensionToMimetypeMap.put("man", "application/x-troff-man");
        this.extensionToMimetypeMap.put("math", "text/xml");
        this.extensionToMimetypeMap.put("mdb", "application/msaccess");
        this.extensionToMimetypeMap.put("mfp", "application/x-shockwave-flash");
        this.extensionToMimetypeMap.put("mht", "message/rfc822");
        this.extensionToMimetypeMap.put("mhtml", "message/rfc822");
        this.extensionToMimetypeMap.put("mi", "application/x-mi");
        this.extensionToMimetypeMap.put("mid", "audio/mid");
        this.extensionToMimetypeMap.put("midi", "audio/mid");
        this.extensionToMimetypeMap.put("mil", "application/x-mil");
        this.extensionToMimetypeMap.put("mml", "text/xml");
        this.extensionToMimetypeMap.put("mnd", "audio/x-musicnet-download");
        this.extensionToMimetypeMap.put("mns", "audio/x-musicnet-stream");
        this.extensionToMimetypeMap.put("mocha", "application/x-javascript");
        this.extensionToMimetypeMap.put("mov", "video/quicktime");
        this.extensionToMimetypeMap.put("movie", "video/x-sgi-movie");
        this.extensionToMimetypeMap.put("mp1", "audio/mp1");
        this.extensionToMimetypeMap.put("mp2", "audio/mp2");
        this.extensionToMimetypeMap.put("mp2v", "video/mpeg");
        this.extensionToMimetypeMap.put("mp3", "audio/mp3");
        this.extensionToMimetypeMap.put("mp4", "video/mp4");
        this.extensionToMimetypeMap.put("mp4a", "audio/mp4");
        this.extensionToMimetypeMap.put("mp4v", "video/mp4");
        this.extensionToMimetypeMap.put("mpa", "video/x-mpg");
        this.extensionToMimetypeMap.put("mpd", "application/vnd.ms-project");
        this.extensionToMimetypeMap.put("mpe", "video/x-mpeg");
        this.extensionToMimetypeMap.put("mpeg", "video/mpg");
        this.extensionToMimetypeMap.put("mpg", "video/mpg");
        this.extensionToMimetypeMap.put("mpg4", "video/mp4");
        this.extensionToMimetypeMap.put("mpga", "audio/rn-mpeg");
        this.extensionToMimetypeMap.put("mpp", "application/vnd.ms-project");
        this.extensionToMimetypeMap.put("mps", "video/x-mpeg");
        this.extensionToMimetypeMap.put("mpt", "application/vnd.ms-project");
        this.extensionToMimetypeMap.put("mpv", "video/mpg");
        this.extensionToMimetypeMap.put("mpv2", "video/mpeg");
        this.extensionToMimetypeMap.put("mpw", "application/vnd.ms-project");
        this.extensionToMimetypeMap.put("mpx", "application/vnd.ms-project");
        this.extensionToMimetypeMap.put("mtx", "text/xml");
        this.extensionToMimetypeMap.put("mxp", "application/x-mmxp");
        this.extensionToMimetypeMap.put("net", "image/pnetvue");
        this.extensionToMimetypeMap.put("nrf", "application/x-nrf");
        this.extensionToMimetypeMap.put("nws", "message/rfc822");
        this.extensionToMimetypeMap.put("odc", "text/x-ms-odc");
        this.extensionToMimetypeMap.put("oga", "audio/ogg");
        this.extensionToMimetypeMap.put("ogg", "audio/ogg");
        this.extensionToMimetypeMap.put("ogv", "video/ogg");
        this.extensionToMimetypeMap.put("ogx", "application/ogg");
        this.extensionToMimetypeMap.put("out", "application/x-out");
        this.extensionToMimetypeMap.put("p10", "application/pkcs10");
        this.extensionToMimetypeMap.put("p12", "application/x-pkcs12");
        this.extensionToMimetypeMap.put("p7b", "application/x-pkcs7-certificates");
        this.extensionToMimetypeMap.put("p7c", "application/pkcs7-mime");
        this.extensionToMimetypeMap.put("p7m", "application/pkcs7-mime");
        this.extensionToMimetypeMap.put("p7r", "application/x-pkcs7-certreqresp");
        this.extensionToMimetypeMap.put("p7s", "application/pkcs7-signature");
        this.extensionToMimetypeMap.put("pbm", "image/x-portable-bitmap");
        this.extensionToMimetypeMap.put("pc5", "application/x-pc5");
        this.extensionToMimetypeMap.put("pci", "application/x-pci");
        this.extensionToMimetypeMap.put("pcl", "application/x-pcl");
        this.extensionToMimetypeMap.put("pcx", "application/x-pcx");
        this.extensionToMimetypeMap.put("pdf", "application/pdf");
        this.extensionToMimetypeMap.put("pdx", "application/vnd.adobe.pdx");
        this.extensionToMimetypeMap.put("pfx", "application/x-pkcs12");
        this.extensionToMimetypeMap.put("pgl", "application/x-pgl");
        this.extensionToMimetypeMap.put("pgm", "image/x-portable-graymap");
        this.extensionToMimetypeMap.put("pic", "application/x-pic");
        this.extensionToMimetypeMap.put("pko", "application/vnd.ms-pki.pko");
        this.extensionToMimetypeMap.put("pl", "application/x-perl");
        this.extensionToMimetypeMap.put("plg", "text/html");
        this.extensionToMimetypeMap.put("pls", "audio/scpls");
        this.extensionToMimetypeMap.put("plt", "application/x-plt");
        this.extensionToMimetypeMap.put("png", "image/png");
        this.extensionToMimetypeMap.put("pnm", "image/x-portable-anymap");
        this.extensionToMimetypeMap.put("pot", "application/vnd.ms-powerpoint");
        this.extensionToMimetypeMap.put("ppa", "application/vnd.ms-powerpoint");
        this.extensionToMimetypeMap.put("ppm", "application/x-ppm");
        this.extensionToMimetypeMap.put("pps", "application/vnd.ms-powerpoint");
        this.extensionToMimetypeMap.put("ppt", "application/vnd.ms-powerpoint");
        this.extensionToMimetypeMap.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        this.extensionToMimetypeMap.put("pr", "application/x-pr");
        this.extensionToMimetypeMap.put("prf", "application/pics-rules");
        this.extensionToMimetypeMap.put("prn", "application/x-prn");
        this.extensionToMimetypeMap.put("prt", "application/x-prt");
        this.extensionToMimetypeMap.put("ps", "application/postscript");
        this.extensionToMimetypeMap.put("ptn", "application/x-ptn");
        this.extensionToMimetypeMap.put("pwz", "application/vnd.ms-powerpoint");
        this.extensionToMimetypeMap.put("qt", "video/quicktime");
        this.extensionToMimetypeMap.put("r3t", "text/vnd.rn-realtext3d");
        this.extensionToMimetypeMap.put("ra", "audio/vnd.rn-realaudio");
        this.extensionToMimetypeMap.put("ram", "audio/x-pn-realaudio");
        this.extensionToMimetypeMap.put("rar", "application/x-rar-compressed");
        this.extensionToMimetypeMap.put("ras", "application/x-ras");
        this.extensionToMimetypeMap.put("rat", "application/rat-file");
        this.extensionToMimetypeMap.put("rdf", "text/xml");
        this.extensionToMimetypeMap.put("rec", "application/vnd.rn-recording");
        this.extensionToMimetypeMap.put("red", "application/x-red");
        this.extensionToMimetypeMap.put("rgb", "application/x-rgb");
        this.extensionToMimetypeMap.put("rjs", "application/vnd.rn-realsystem-rjs");
        this.extensionToMimetypeMap.put("rjt", "application/vnd.rn-realsystem-rjt");
        this.extensionToMimetypeMap.put("rlc", "application/x-rlc");
        this.extensionToMimetypeMap.put("rle", "application/x-rle");
        this.extensionToMimetypeMap.put("rm", "application/vnd.rn-realmedia");
        this.extensionToMimetypeMap.put("rmf", "application/vnd.adobe.rmf");
        this.extensionToMimetypeMap.put("rmi", "audio/mid");
        this.extensionToMimetypeMap.put("rmj", "application/vnd.rn-realsystem-rmj");
        this.extensionToMimetypeMap.put("rmm", "audio/x-pn-realaudio");
        this.extensionToMimetypeMap.put("rmp", "application/vnd.rn-rn_music_package");
        this.extensionToMimetypeMap.put("rms", "application/vnd.rn-realmedia-secure");
        this.extensionToMimetypeMap.put("rmvb", "application/vnd.rn-realmedia-vbr");
        this.extensionToMimetypeMap.put("rmx", "application/vnd.rn-realsystem-rmx");
        this.extensionToMimetypeMap.put("rnx", "application/vnd.rn-realplayer");
        this.extensionToMimetypeMap.put("rp", "image/vnd.rn-realpix");
        this.extensionToMimetypeMap.put("rpm", "audio/x-pn-realaudio-plugin");
        this.extensionToMimetypeMap.put("rsml", "application/vnd.rn-rsml");
        this.extensionToMimetypeMap.put("rss", "application/rss+xml");
        this.extensionToMimetypeMap.put("rt", "text/vnd.rn-realtext");
        this.extensionToMimetypeMap.put("rtf", "application/x-rtf");
        this.extensionToMimetypeMap.put("rv", "video/vnd.rn-realvideo");
        this.extensionToMimetypeMap.put("sam", "application/x-sam");
        this.extensionToMimetypeMap.put("sat", "application/x-sat");
        this.extensionToMimetypeMap.put("sdp", "application/sdp");
        this.extensionToMimetypeMap.put("sdw", "application/x-sdw");
        this.extensionToMimetypeMap.put("sgm", "text/sgml");
        this.extensionToMimetypeMap.put("sgml", "text/sgml");
        this.extensionToMimetypeMap.put("sis", "application/vnd.symbian.install");
        this.extensionToMimetypeMap.put("sisx", "application/vnd.symbian.install");
        this.extensionToMimetypeMap.put("sit", "application/x-stuffit");
        this.extensionToMimetypeMap.put("slb", "application/x-slb");
        this.extensionToMimetypeMap.put("sld", "application/x-sld");
        this.extensionToMimetypeMap.put("slk", "drawing/x-slk");
        this.extensionToMimetypeMap.put("smi", "application/smil");
        this.extensionToMimetypeMap.put("smil", "application/smil");
        this.extensionToMimetypeMap.put("smk", "application/x-smk");
        this.extensionToMimetypeMap.put("snd", "audio/basic");
        this.extensionToMimetypeMap.put("sol", "text/plain");
        this.extensionToMimetypeMap.put("sor", "text/plain");
        this.extensionToMimetypeMap.put("spc", "application/x-pkcs7-certificates");
        this.extensionToMimetypeMap.put("spl", "application/futuresplash");
        this.extensionToMimetypeMap.put("spp", "text/xml");
        this.extensionToMimetypeMap.put("ssm", "application/streamingmedia");
        this.extensionToMimetypeMap.put("sst", "application/vnd.ms-pki.certstore");
        this.extensionToMimetypeMap.put("stl", "application/vnd.ms-pki.stl");
        this.extensionToMimetypeMap.put("stm", "text/html");
        this.extensionToMimetypeMap.put("sty", "application/x-sty");
        this.extensionToMimetypeMap.put("svg", "text/xml");
        this.extensionToMimetypeMap.put("swf", "application/x-shockwave-flash");
        this.extensionToMimetypeMap.put("tar", "application/x-tar");
        this.extensionToMimetypeMap.put("tdf", "application/x-tdf");
        this.extensionToMimetypeMap.put("tg4", "application/x-tg4");
        this.extensionToMimetypeMap.put("tga", "application/x-tga");
        this.extensionToMimetypeMap.put("tif", "image/tiff");
        this.extensionToMimetypeMap.put("tiff", "image/tiff");
        this.extensionToMimetypeMap.put("tld", "text/xml");
        this.extensionToMimetypeMap.put("top", "drawing/x-top");
        this.extensionToMimetypeMap.put("torrent", "application/x-bittorrent");
        this.extensionToMimetypeMap.put("tsd", "text/xml");
        this.extensionToMimetypeMap.put("ttf", "application/x-font-ttf");
        this.extensionToMimetypeMap.put("txt", "text/plain");
        this.extensionToMimetypeMap.put("uin", "application/x-icq");
        this.extensionToMimetypeMap.put("uls", "text/iuls");
        this.extensionToMimetypeMap.put("vcf", "text/x-vcard");
        this.extensionToMimetypeMap.put("vda", "application/x-vda");
        this.extensionToMimetypeMap.put("vdx", "application/vnd.visio");
        this.extensionToMimetypeMap.put("vml", "text/xml");
        this.extensionToMimetypeMap.put("vpg", "application/x-vpeg005");
        this.extensionToMimetypeMap.put("vsd", "application/vnd.visio");
        this.extensionToMimetypeMap.put("vss", "application/vnd.visio");
        this.extensionToMimetypeMap.put("vst", "application/x-vst");
        this.extensionToMimetypeMap.put("vsw", "application/vnd.visio");
        this.extensionToMimetypeMap.put("vsx", "application/vnd.visio");
        this.extensionToMimetypeMap.put("vtx", "application/vnd.visio");
        this.extensionToMimetypeMap.put("vxml", "text/xml");
        this.extensionToMimetypeMap.put("wav", "audio/wav");
        this.extensionToMimetypeMap.put("wax", "audio/x-ms-wax");
        this.extensionToMimetypeMap.put("wb1", "application/x-wb1");
        this.extensionToMimetypeMap.put("wb2", "application/x-wb2");
        this.extensionToMimetypeMap.put("wb3", "application/x-wb3");
        this.extensionToMimetypeMap.put("wbmp", "image/vnd.wap.wbmp");
        this.extensionToMimetypeMap.put("webm", "video/webm");
        this.extensionToMimetypeMap.put("wiz", "application/msword");
        this.extensionToMimetypeMap.put("wk3", "application/x-wk3");
        this.extensionToMimetypeMap.put("wk4", "application/x-wk4");
        this.extensionToMimetypeMap.put("wkq", "application/x-wkq");
        this.extensionToMimetypeMap.put("wks", "application/x-wks");
        this.extensionToMimetypeMap.put("wm", "video/x-ms-wm");
        this.extensionToMimetypeMap.put("wma", "audio/x-ms-wma");
        this.extensionToMimetypeMap.put("wmd", "application/x-ms-wmd");
        this.extensionToMimetypeMap.put("wmf", "application/x-wmf");
        this.extensionToMimetypeMap.put("wml", "text/vnd.wap.wml");
        this.extensionToMimetypeMap.put("wmv", "video/x-ms-wmv");
        this.extensionToMimetypeMap.put("wmx", "video/x-ms-wmx");
        this.extensionToMimetypeMap.put("wmz", "application/x-ms-wmz");
        this.extensionToMimetypeMap.put("woff", "application/x-font-woff");
        this.extensionToMimetypeMap.put("wp6", "application/x-wp6");
        this.extensionToMimetypeMap.put("wpd", "application/x-wpd");
        this.extensionToMimetypeMap.put("wpg", "application/x-wpg");
        this.extensionToMimetypeMap.put("wpl", "application/vnd.ms-wpl");
        this.extensionToMimetypeMap.put("wq1", "application/x-wq1");
        this.extensionToMimetypeMap.put("wr1", "application/x-wr1");
        this.extensionToMimetypeMap.put("wri", "application/x-wri");
        this.extensionToMimetypeMap.put("wrk", "application/x-wrk");
        this.extensionToMimetypeMap.put("ws", "application/x-ws");
        this.extensionToMimetypeMap.put("ws2", "application/x-ws");
        this.extensionToMimetypeMap.put("wsc", "text/scriptlet");
        this.extensionToMimetypeMap.put("wsdl", "text/xml");
        this.extensionToMimetypeMap.put("wvx", "video/x-ms-wvx");
        this.extensionToMimetypeMap.put("x_b", "application/x-x_b");
        this.extensionToMimetypeMap.put("x_t", "application/x-x_t");
        this.extensionToMimetypeMap.put("xap", "application/x-silverlight-app");
        this.extensionToMimetypeMap.put("xbm", "image/x-xbitmap");
        this.extensionToMimetypeMap.put("xdp", "application/vnd.adobe.xdp");
        this.extensionToMimetypeMap.put("xdr", "text/xml");
        this.extensionToMimetypeMap.put("xfd", "application/vnd.adobe.xfd");
        this.extensionToMimetypeMap.put("xfdf", "application/vnd.adobe.xfdf");
        this.extensionToMimetypeMap.put("xhtml", "text/html");
        this.extensionToMimetypeMap.put("xls", "application/vnd.ms-excel");
        this.extensionToMimetypeMap.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        this.extensionToMimetypeMap.put("xlw", "application/x-xlw");
        this.extensionToMimetypeMap.put("xml", "text/xml");
        this.extensionToMimetypeMap.put("xpl", "audio/scpls");
        this.extensionToMimetypeMap.put("xpm", "image/x-xpixmap");
        this.extensionToMimetypeMap.put("xq", "text/xml");
        this.extensionToMimetypeMap.put("xql", "text/xml");
        this.extensionToMimetypeMap.put("xquery", "text/xml");
        this.extensionToMimetypeMap.put("xsd", "text/xml");
        this.extensionToMimetypeMap.put("xsl", "text/xml");
        this.extensionToMimetypeMap.put("xslt", "text/xml");
        this.extensionToMimetypeMap.put("xwd", "application/x-xwd");
        this.extensionToMimetypeMap.put("yaml", "text/yaml");
        this.extensionToMimetypeMap.put("yml", "text/yaml");
        this.extensionToMimetypeMap.put("zip", "application/zip");
    }

    public static Mimetypes getInstance() {
        return MimetypesHolder.mimetypes;
    }

    public String getMimetype(File file) {
        return getMimetype(file.getName());
    }

    public String getMimetype(String str) {
        int i;
        String str2 = this.extensionToMimetypeMap.get("*");
        if (str2 == null) {
            str2 = "application/octet-stream";
        }
        int iLastIndexOf = str.lastIndexOf(".");
        if (iLastIndexOf > 0 && (i = iLastIndexOf + 1) < str.length()) {
            String strSubstring = str.substring(i);
            if (this.extensionToMimetypeMap.keySet().contains(strSubstring)) {
                String str3 = this.extensionToMimetypeMap.get(strSubstring);
                if (log.isDebugEnabled()) {
                    log.debug((CharSequence) ("Recognised extension '" + strSubstring + "', mimetype is: '" + str3 + "'"));
                }
                return str3;
            }
            if (log.isDebugEnabled()) {
                log.debug((CharSequence) ("Extension '" + strSubstring + "' is unrecognized in mime type listing, using default mime type: '" + str2 + "'"));
            }
        } else if (log.isDebugEnabled()) {
            log.debug((CharSequence) ("File name has no extension, mime type cannot be recognised for: " + str));
        }
        return str2;
    }

    public void loadAndReplaceMimetypes(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                return;
            }
            String strTrim = line.trim();
            if (!strTrim.startsWith("#") && strTrim.length() != 0) {
                StringTokenizer stringTokenizer = new StringTokenizer(strTrim, " \t");
                if (stringTokenizer.countTokens() > 1) {
                    String strNextToken = stringTokenizer.nextToken();
                    while (stringTokenizer.hasMoreTokens()) {
                        String strNextToken2 = stringTokenizer.nextToken();
                        this.extensionToMimetypeMap.put(strNextToken2, strNextToken);
                        if (log.isDebugEnabled()) {
                            log.debug((CharSequence) ("Setting mime type for extension '" + strNextToken2 + "' to '" + strNextToken + "'"));
                        }
                    }
                } else if (log.isDebugEnabled()) {
                    log.debug((CharSequence) ("Ignoring mimetype with no associated file extensions: '" + strTrim + "'"));
                }
            }
        }
    }
}
