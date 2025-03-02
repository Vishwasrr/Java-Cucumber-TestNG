package com.mylaptop.asts.test;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class GitHubAutomation {
    private static final String GITHUB_TOKEN = System.getenv("GIT_ACCESS_TOKEN"); // Add your token here
    private static final String REPO_OWNER = "vishwasrr"; // Change this
    private static final String REPO_NAME = "New-Prompt-Junit";   // Change this
    private static final int LAST_N_MINUTES = 30; // Fetch PRs closed in the last N minutes

    public static void main(String[] args) {
        fetchClosedPRs();
    }

    private static void fetchClosedPRs() {
        try {
            String apiUrl = String.format("https://api.github.com/repos/%s/%s/pulls?state=closed", REPO_OWNER,
                    REPO_NAME);
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "token " + GITHUB_TOKEN);
            conn.setRequestProperty("Accept", "application/vnd.github.v3+json");

            if (conn.getResponseCode() != 200) {
                System.out.println("Failed: HTTP error code: " + conn.getResponseCode());
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            conn.disconnect();

            // Process PRs
            System.out.println(response.toString());
            processPRs(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processPRs(String jsonResponse) {
        // Convert current time to GitHub's timestamp format
        Instant currentTime = Instant.now();
        Instant cutoffTime = currentTime.minusSeconds(LAST_N_MINUTES * 60);
        int count = 0;
        // Parse JSON response (use Jackson or Gson for proper parsing)
        if (jsonResponse.contains("closed_at")) {
            String[] prs = jsonResponse.split("},\\{");
            for (String pr : prs) {
                if (pr.contains("\"closed_at\"")) {
                    String closedAt = pr.split("\"closed_at\":\"")[1].split("\"")[0];

                    Instant closedInstant = Instant.parse(closedAt);
                    if (closedInstant.isAfter(cutoffTime)) {
                        count++;
//                        System.out.println("Closed PR: " + new JSONArray(pr).toString(2));
                        System.out.println("Closed PR: " + pr);
                    }
                }
            }
        }
        System.out.println(count);
    }
}

//git filter-branch --force --index-filter "git rm --cached --ignore-unmatch src/main/java/com/mylaptop/asts/test/GitHubAutomation.java"--prune-empty --tag-name-
//filter cat ----all
