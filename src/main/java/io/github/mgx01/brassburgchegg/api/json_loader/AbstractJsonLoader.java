package io.github.mgx01.brassburgchegg.api.json_loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import net.neoforged.fml.loading.FMLPaths;
import org.slf4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AbstractJsonLoader<T> {
    protected final Logger logger = LogUtils.getLogger();
    protected final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final Class<T> formatClass;
    private final String internalPath;
    private final Path externalPath;

    public AbstractJsonLoader(Class<T> formatClass, String internalPath, String targetFileName) {
        this.formatClass = formatClass;
        this.internalPath = internalPath;
        this.externalPath = FMLPaths.CONFIGDIR.get().resolve(targetFileName);
        logger.info("!!! THE MOD IS READING THE CONFIG FROM EXACTLY THIS FOLDER: {} !!!", this.externalPath.toAbsolutePath());
    }


    public void load() {
        ensureConfigFileExists();

        try {
            T externalData = loadConfigFromFile();
            T internalData = loadInternalDefaults();
            // SYNC EXTERNAL TO BASE JSON - GSON HANDLES DUMB ADMIN BEHAVIOR DONT WORRY
            if (externalData != null && internalData != null) {
                if (synchronizeWithInternalDefaults(internalData, externalData)) {
                    saveConfigToFile(externalData);
                }
                applyToGameMemory(externalData);
            }
        } catch (Exception e) {
            logger.error("BrassburgChegg: Error processing JSON config for {}", externalPath.getFileName(), e);
        }
    }

    private void ensureConfigFileExists() {
        if (!Files.exists(externalPath)) {
            T master = loadInternalDefaults();
            if (master != null) {
                saveConfigToFile(master);
                logger.info("BrassburgChegg: Initialized default config '{}' from master template.", externalPath.getFileName());
            }
        }
    }

    private T loadConfigFromFile() throws IOException {
        try (Reader reader = Files.newBufferedReader(externalPath)) {
            return gson.fromJson(reader, formatClass);
        }
    }

    private T loadInternalDefaults() {
        try (InputStream is = getClass().getResourceAsStream(internalPath);
             Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, formatClass);
        } catch (Exception e) {
            logger.error("CRITICAL: Internal master template missing at {}!", internalPath, e);
            return null;
        }
    }

    private void saveConfigToFile(T data) {
        try (Writer writer = Files.newBufferedWriter(externalPath)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            logger.error("BrassburgChegg: Failed to write to config file {}", externalPath.getFileName(), e);
        }
    }

    protected abstract boolean synchronizeWithInternalDefaults(T masterData, T userData);

    protected abstract void applyToGameMemory(T data);
}