package org.example.reqres.pojo.test;

public class Response {
    private Data data;
    private Support support;

    public Data getData() {
        return data;
    }

    public Support getSupport() {
        return support;
    }

    public static class Data {
        private String lastName;
        private int id;
        private String avatar;
        private String firstName;
        private String email;

        public String getLastName() {
            return lastName;
        }

        public int getId() {
            return id;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getEmail() {
            return email;
        }
    }

    public static class Support {
        private String text;
        private String url;

        public String getText() {
            return text;
        }

        public String getUrl() {
            return url;
        }
    }
}