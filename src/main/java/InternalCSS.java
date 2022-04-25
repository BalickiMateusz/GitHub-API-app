public class InternalCSS {

    public String getCSS(){

        String css = "dl {\n" +
                "    border: 3px double #ccc;\n" +
                "    padding: 0.5em;\n" +
                "  }\n" +
                "  dt {\n" +
                "    float: left;\n" +
                "    clear: left;\n" +
                "    width: 100px;\n" +
                "    text-align: right;\n" +
                "    font-weight: bold;\n" +
                "    color: green;\n" +
                "  }\n" +
                "  dt::after {\n" +
                "    content: \":\";\n" +
                "  }\n" +
                "  dd {\n" +
                "    margin: 0 0 0 110px;\n" +
                "    padding: 0 0 0.5em 0;\n" +
                "  }h1{\n" +
                "  font-size: 32px;\n" +
                "  text-shadow: -1px -1px #0c0, 1px 1px #060, -3px 0 4px #000;\n" +
                "  font-family:Arial, Helvetica, sans-serif;\n" +
                "  color: black;\n" +
                "  padding:16px;\n" +
                "  font-weight:lighter;\n" +
                "  }h2{\n" +
                "  font-size: 28px;\n" +
                "  font-family:Arial, Helvetica, sans-serif;\n" +
                "  color: red;\n" +
                "  padding: 16px;\n" +
                "  font-weight:lighter;}" +
                "  body {\n" +
                "  background-color: linen;\n" +
                "  }" +
                "  ul {\n" +
                "  border: 3px double #ccc;\n" +
                "  padding: 0.5em;\n" +
                "  }\n" +
                "  ul ul{border: 0px; padding: 0.5em; color: red;}\n" +
                "  li {\n" +
                "    margin: 0 0 0 110px;\n" +
                "  }\n";

        return css;
    }

}
