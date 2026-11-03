package edu.cis221.weather;

import edu.cis221.weather.WeatherService.CityNotFoundException;
import edu.cis221.weather.WeatherService.WeatherSnapshot;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public final class WeatherApp extends JFrame {
    private static final Color BACKGROUND = new Color(241, 245, 241);
    private static final Color FOREST = new Color(27, 62, 53);
    private static final Color INK = new Color(29, 42, 36);
    private static final Color MUTED = new Color(105, 118, 110);
    private static final Color LINE = new Color(220, 228, 221);
    private static final Color ORANGE = new Color(224, 112, 73);

    private final JTextField cityField = new JTextField();
    private final JButton searchButton = new JButton("Search");
    private final JLabel locationLabel = new JLabel("Your location", SwingConstants.CENTER);
    private final JLabel temperatureLabel = new JLabel("--°", SwingConstants.CENTER);
    private final JLabel conditionLabel = new JLabel("Search for a city", SwingConstants.CENTER);
    private final JLabel feelsLikeLabel = new JLabel("Feels like --°C", SwingConstants.CENTER);
    private final JLabel humidityValue = new JLabel("--", SwingConstants.CENTER);
    private final JLabel windValue = new JLabel("--", SwingConstants.CENTER);
    private final JLabel statusLabel = new JLabel("Enter a city to see current conditions.");
    private final JLabel updatedLabel = new JLabel("Weather data by Open-Meteo");

    public WeatherApp() {
        super("Weather Desk");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(620, 560));
        setSize(760, 640);
        setLocationRelativeTo(null);
        setContentPane(createContent());

        searchButton.addActionListener((ActionEvent event) -> {
            searchWeather();
        });
        cityField.addActionListener(event -> searchWeather());
    }

    private JPanel createContent() {
        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setBackground(BACKGROUND);
        content.setBorder(BorderFactory.createEmptyBorder(24, 28, 20, 28));

        content.add(createHeader(), BorderLayout.NORTH);
        content.add(createMainContent(), BorderLayout.CENTER);
        content.add(createFooter(), BorderLayout.SOUTH);
        return content;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(FOREST);
        header.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        JPanel titles = new JPanel(new GridLayout(2, 1, 0, 4));
        titles.setOpaque(false);
        JLabel eyebrow = label("WEATHER / NOW", 11, Font.BOLD, new Color(187, 211, 190));
        JLabel title = label("Weather Desk", 27, Font.BOLD, Color.WHITE);
        titles.add(eyebrow);
        titles.add(title);
        header.add(titles, BorderLayout.WEST);

        JLabel liveLabel = label("LIVE CONDITIONS", 11, Font.BOLD, new Color(243, 185, 133));
        header.add(liveLabel, BorderLayout.EAST);
        return header;
    }

    private JPanel createMainContent() {
        JPanel main = new JPanel(new BorderLayout(0, 18));
        main.setOpaque(false);
        main.add(createSearchPanel(), BorderLayout.NORTH);
        main.add(createWeatherPanel(), BorderLayout.CENTER);
        return main;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setOpaque(false);

        JLabel cityLabel = label("CITY", 11, Font.BOLD, MUTED);
        panel.add(cityLabel, BorderLayout.NORTH);

        JPanel controls = new JPanel(new BorderLayout(10, 0));
        controls.setOpaque(false);
        cityField.setFont(new Font("Dialog", Font.PLAIN, 15));
        cityField.setForeground(INK);
        cityField.setBackground(Color.WHITE);
        cityField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)));
        cityField.setToolTipText("Enter a city, optionally followed by a state or country");
        controls.add(cityField, BorderLayout.CENTER);

        searchButton.setFont(new Font("Dialog", Font.BOLD, 14));
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(ORANGE);
        searchButton.setFocusPainted(false);
        searchButton.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        searchButton.setOpaque(true);
        controls.add(searchButton, BorderLayout.EAST);
        panel.add(controls, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createWeatherPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 16));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE),
                BorderFactory.createEmptyBorder(24, 24, 22, 24)));

        JPanel summary = new JPanel(new BorderLayout(0, 10));
        summary.setOpaque(false);
        locationLabel.setFont(new Font("Dialog", Font.BOLD, 19));
        locationLabel.setForeground(INK);
        summary.add(locationLabel, BorderLayout.NORTH);

        temperatureLabel.setFont(new Font("Georgia", Font.PLAIN, 70));
        temperatureLabel.setForeground(FOREST);
        summary.add(temperatureLabel, BorderLayout.CENTER);

        JPanel condition = new JPanel(new GridLayout(2, 1, 0, 5));
        condition.setOpaque(false);
        conditionLabel.setFont(new Font("Dialog", Font.BOLD, 17));
        conditionLabel.setForeground(INK);
        feelsLikeLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        feelsLikeLabel.setForeground(MUTED);
        condition.add(conditionLabel);
        condition.add(feelsLikeLabel);
        summary.add(condition, BorderLayout.SOUTH);
        panel.add(summary, BorderLayout.CENTER);

        JPanel metrics = new JPanel(new GridLayout(1, 2, 12, 0));
        metrics.setOpaque(false);
        metrics.add(createMetric("HUMIDITY", humidityValue, "%"));
        metrics.add(createMetric("WIND", windValue, "km/h"));
        panel.add(metrics, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createMetric(String title, JLabel value, String unit) {
        JPanel metric = new JPanel(new BorderLayout(0, 5));
        metric.setBackground(BACKGROUND);
        metric.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));
        JLabel titleLabel = label(title, 10, Font.BOLD, MUTED);
        value.setFont(new Font("Dialog", Font.BOLD, 21));
        value.setForeground(INK);
        JLabel unitLabel = label(unit, 12, Font.PLAIN, MUTED);

        JPanel reading = new JPanel(new BorderLayout(6, 0));
        reading.setOpaque(false);
        reading.add(value, BorderLayout.WEST);
        reading.add(unitLabel, BorderLayout.CENTER);
        metric.add(titleLabel, BorderLayout.NORTH);
        metric.add(reading, BorderLayout.CENTER);
        return metric;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel(new BorderLayout(12, 0));
        footer.setOpaque(false);
        statusLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        statusLabel.setForeground(MUTED);
        updatedLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
        updatedLabel.setForeground(MUTED);
        footer.add(statusLabel, BorderLayout.WEST);
        footer.add(updatedLabel, BorderLayout.EAST);
        return footer;
    }

    private JLabel label(String text, int size, int style, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Dialog", style, size));
        label.setForeground(color);
        return label;
    }

    private void searchWeather() {
        final String city = cityField.getText().trim();
        if (city.isEmpty()) {
            statusLabel.setText("Enter a city name to search.");
            cityField.requestFocusInWindow();
            return;
        }

        setBusy(true);
        statusLabel.setText("Looking up " + city + "...");
        new SwingWorker<WeatherSnapshot, Void>() {
            @Override
            protected WeatherSnapshot doInBackground() throws Exception {
                return new WeatherService().fetchCurrentWeather(city);
            }

            @Override
            protected void done() {
                try {
                    displayWeather(get());
                    statusLabel.setText("Current conditions loaded.");
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                    statusLabel.setText("Search interrupted. Please try again.");
                } catch (java.util.concurrent.ExecutionException exception) {
                    Throwable cause = exception.getCause();
                    if (cause instanceof CityNotFoundException) {
                        statusLabel.setText("No matching city. Try adding a state or country.");
                    } else {
                        statusLabel.setText("Could not load weather. Check your connection and try again.");
                    }
                } finally {
                    setBusy(false);
                }
            }
        }.execute();
    }

    private void displayWeather(WeatherSnapshot weather) {
        DecimalFormat temperatureFormat = new DecimalFormat("0");
        DecimalFormat detailFormat = new DecimalFormat("0.0");
        locationLabel.setText(weather.getLocation());
        temperatureLabel.setText(temperatureFormat.format(weather.getTemperature()) + "°");
        conditionLabel.setText(weather.getCondition());
        feelsLikeLabel.setText("Feels like " + detailFormat.format(weather.getApparentTemperature()) + "°C");
        humidityValue.setText(temperatureFormat.format(weather.getHumidity()));
        windValue.setText(temperatureFormat.format(weather.getWindSpeed()));
        if (!weather.getTime().isEmpty()) {
            updatedLabel.setText("Local time " + weather.getTime());
        }
    }

    private void setBusy(boolean busy) {
        searchButton.setEnabled(!busy);
        searchButton.setText(busy ? "Searching..." : "Search");
        cityField.setEnabled(!busy);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WeatherApp().setVisible(true);
            }
        });
    }
}