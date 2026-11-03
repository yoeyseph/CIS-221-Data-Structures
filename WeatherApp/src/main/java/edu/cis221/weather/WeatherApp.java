package edu.cis221.weather;

import edu.cis221.weather.WeatherService.CityNotFoundException;
import edu.cis221.weather.WeatherService.HourlyForecast;
import edu.cis221.weather.WeatherService.WeatherSnapshot;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.List;

public final class WeatherApp extends JFrame {
    private static final Color BACKGROUND = new Color(241, 245, 241);
    private static final Color FOREST = new Color(27, 62, 53);
    private static final Color INK = new Color(29, 42, 36);
    private static final Color MUTED = new Color(105, 118, 110);
    private static final Color LINE = new Color(220, 228, 221);
    private static final Color ORANGE = new Color(224, 112, 73);

    private final JTextField cityField = new JTextField();
    private final JButton searchButton = new JButton("Search");
    private final JToggleButton celsiusButton = new JToggleButton("°C", true);
    private final JToggleButton fahrenheitButton = new JToggleButton("°F");
    private final JLabel locationLabel = new JLabel("Your location", SwingConstants.CENTER);
    private final JLabel temperatureLabel = new JLabel("--°", SwingConstants.CENTER);
    private final JLabel conditionLabel = new JLabel("Search for a city", SwingConstants.CENTER);
    private final JLabel feelsLikeLabel = new JLabel("Feels like --°", SwingConstants.CENTER);
    private final JLabel humidityValue = new JLabel("--", SwingConstants.CENTER);
    private final JLabel windValue = new JLabel("--", SwingConstants.CENTER);
    private final JLabel statusLabel = new JLabel("Enter a city to see current conditions.");
    private final JLabel updatedLabel = new JLabel("Weather data by Open-Meteo");
    private final JLabel forecastStatusLabel = new JLabel("");
    private final DefaultTableModel hourlyForecastModel = new DefaultTableModel(
            new Object[]{"TIME", "TEMP", "PRECIP (in)", "CHANCE", "CLOUD COVER", "WIND (km/h)"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable hourlyForecastTable = new JTable(hourlyForecastModel);
    private WeatherSnapshot currentWeather;

    public WeatherApp() {
        super("Weather Desk");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(780, 700));
        setSize(920, 820);
        setLocationRelativeTo(null);
        setContentPane(createContent());
        configureTemperatureControls();

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

        JPanel currentConditions = new JPanel(new BorderLayout(0, 16));
        currentConditions.setOpaque(false);

        JPanel summary = new JPanel(new BorderLayout(0, 10));
        summary.setOpaque(false);
        locationLabel.setFont(new Font("Dialog", Font.BOLD, 19));
        locationLabel.setForeground(INK);
        summary.add(locationLabel, BorderLayout.NORTH);

        JPanel temperatureRow = new JPanel(new BorderLayout(12, 0));
        temperatureRow.setOpaque(false);
        temperatureLabel.setFont(new Font("Georgia", Font.PLAIN, 70));
        temperatureLabel.setForeground(FOREST);
        temperatureRow.add(temperatureLabel, BorderLayout.CENTER);
        JPanel unitButtons = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));
        unitButtons.setOpaque(false);
        unitButtons.add(celsiusButton);
        unitButtons.add(fahrenheitButton);
        temperatureRow.add(unitButtons, BorderLayout.EAST);
        summary.add(temperatureRow, BorderLayout.CENTER);

        JPanel condition = new JPanel(new GridLayout(2, 1, 0, 5));
        condition.setOpaque(false);
        conditionLabel.setFont(new Font("Dialog", Font.BOLD, 17));
        conditionLabel.setForeground(INK);
        feelsLikeLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        feelsLikeLabel.setForeground(MUTED);
        condition.add(conditionLabel);
        condition.add(feelsLikeLabel);
        summary.add(condition, BorderLayout.SOUTH);
        currentConditions.add(summary, BorderLayout.CENTER);

        JPanel metrics = new JPanel(new GridLayout(1, 2, 12, 0));
        metrics.setOpaque(false);
        metrics.add(createMetric("HUMIDITY", humidityValue, "%"));
        metrics.add(createMetric("WIND", windValue, "km/h"));
        currentConditions.add(metrics, BorderLayout.SOUTH);

        panel.add(currentConditions, BorderLayout.NORTH);
        panel.add(createHourlyForecastPanel(), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createHourlyForecastPanel() {
        JPanel section = new JPanel(new BorderLayout(0, 8));
        section.setOpaque(false);

        JPanel heading = new JPanel(new BorderLayout());
        heading.setOpaque(false);
        heading.add(label("NEXT 8 HOURS", 11, Font.BOLD, MUTED), BorderLayout.WEST);
        forecastStatusLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
        forecastStatusLabel.setForeground(MUTED);
        heading.add(forecastStatusLabel, BorderLayout.EAST);
        section.add(heading, BorderLayout.NORTH);

        hourlyForecastTable.setRowHeight(26);
        hourlyForecastTable.setFillsViewportHeight(true);
        hourlyForecastTable.setShowVerticalLines(false);
        hourlyForecastTable.setGridColor(LINE);
        hourlyForecastTable.setFont(new Font("Dialog", Font.PLAIN, 12));
        hourlyForecastTable.getTableHeader().setReorderingAllowed(false);
        hourlyForecastTable.getTableHeader().setBackground(BACKGROUND);
        hourlyForecastTable.getTableHeader().setForeground(MUTED);
        hourlyForecastTable.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 10));
        int[] columnWidths = {85, 75, 100, 90, 110, 110};
        for (int column = 0; column < columnWidths.length; column++) {
            hourlyForecastTable.getColumnModel().getColumn(column).setPreferredWidth(columnWidths[column]);
        }

        JScrollPane scrollPane = new JScrollPane(hourlyForecastTable);
        scrollPane.setPreferredSize(new Dimension(760, 240));
        scrollPane.setBorder(BorderFactory.createLineBorder(LINE));
        section.add(scrollPane, BorderLayout.CENTER);
        return section;
    }

    private void configureTemperatureControls() {
        ButtonGroup temperatureUnits = new ButtonGroup();
        temperatureUnits.add(celsiusButton);
        temperatureUnits.add(fahrenheitButton);
        styleTemperatureButton(celsiusButton);
        styleTemperatureButton(fahrenheitButton);
        celsiusButton.addActionListener(event -> updateWeatherDisplay());
        fahrenheitButton.addActionListener(event -> updateWeatherDisplay());
    }

    private void styleTemperatureButton(JToggleButton button) {
        button.setFont(new Font("Dialog", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE),
                BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        button.addItemListener(event -> {
            boolean selected = button.isSelected();
            button.setBackground(selected ? FOREST : Color.WHITE);
            button.setForeground(selected ? Color.WHITE : INK);
        });
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
        currentWeather = weather;
        updateWeatherDisplay();
        if (!weather.getTime().isEmpty()) {
            updatedLabel.setText("Local time " + weather.getTime());
        }
    }

    private void updateWeatherDisplay() {
        if (currentWeather == null) {
            return;
        }
        DecimalFormat temperatureFormat = new DecimalFormat("0");
        DecimalFormat detailFormat = new DecimalFormat("0.0");
        boolean fahrenheit = fahrenheitButton.isSelected();
        String temperatureUnit = fahrenheit ? "°F" : "°C";
        locationLabel.setText(currentWeather.getLocation());
        temperatureLabel.setText(temperatureFormat.format(displayTemperature(
                currentWeather.getTemperature(), fahrenheit)) + temperatureUnit);
        conditionLabel.setText(currentWeather.getCondition());
        feelsLikeLabel.setText("Feels like " + detailFormat.format(displayTemperature(
                currentWeather.getApparentTemperature(), fahrenheit)) + temperatureUnit);
        humidityValue.setText(temperatureFormat.format(currentWeather.getHumidity()));
        windValue.setText(temperatureFormat.format(currentWeather.getWindSpeed()));

        hourlyForecastModel.setRowCount(0);
        List<HourlyForecast> hourlyForecast = currentWeather.getHourlyForecast();
        forecastStatusLabel.setText(hourlyForecast.isEmpty() ? "Hourly forecast unavailable." : "");
        for (HourlyForecast hour : hourlyForecast) {
            hourlyForecastModel.addRow(new Object[]{
                    formatForecastTime(hour.getTime()),
                    temperatureFormat.format(displayTemperature(hour.getTemperature(), fahrenheit)) + temperatureUnit,
                    detailFormat.format(hour.getPrecipitation() / 25.4),
                    temperatureFormat.format(hour.getPrecipitationProbability()) + "%",
                    temperatureFormat.format(hour.getCloudCover()) + "%",
                    temperatureFormat.format(hour.getWindSpeed())
            });
        }
    }

    private double displayTemperature(double celsius, boolean fahrenheit) {
        return fahrenheit ? toFahrenheit(celsius) : celsius;
    }

    static double toFahrenheit(double celsius) {
        return celsius * 9.0 / 5.0 + 32.0;
    }

    private String formatForecastTime(String time) {
        int separator = time.indexOf('T');
        if (separator >= 0 && time.length() >= separator + 6) {
            return time.substring(separator + 1, separator + 6);
        }
        return time;
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