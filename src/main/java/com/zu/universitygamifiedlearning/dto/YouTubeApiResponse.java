package com.zu.universitygamifiedlearning.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeApiResponse {
    private Item[] items;

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private Id id;
        private Snippet snippet;

        public Id getId() {
            return id;
        }

        public void setId(Id id) {
            this.id = id;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public void setSnippet(Snippet snippet) {
            this.snippet = snippet;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Id {
            private String videoId;

            public String getVideoId() {
                return videoId;
            }

            public void setVideoId(String videoId) {
                this.videoId = videoId;
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Snippet {
            private String title;
            private String description;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }
}
