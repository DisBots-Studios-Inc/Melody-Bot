package com.disbots.commands.music;

import com.disbots.util.EmbedColors;
import com.disbots.util.emojis.IEmojis;
import com.disbots.util.logging.Log;
import com.disbots.util.logging.LogTypes;
import com.disbots.util.music.LavaPlayerAudioSource;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.disbots.core.Main.DEVELOPER_KEY;
import static com.disbots.core.YoutubeApiService.getService;

public class Play implements CommandExecutor
{
    @Command(aliases = {"play", "pl"}, description = "Plays specified music track", usage = "play <music name>")
    public void OnPlayCommand(MessageCreateEvent message, String[] args)
    {
        CompletableFuture<Message> LoadingMessage = null;
        ServerVoiceChannel voiceChannel = null;

       try
        {
            /*
            TODO: Enable youtube api.

            YouTube youtubeService = getService();
            // Define and execute the API request
            YouTube.Search.List request = youtubeService.search()
                    .list("snippet");
            SearchListResponse response = request.setMaxResults(1L).setKey(DEVELOPER_KEY)
                    .setOrder("viewCount")
                    .setQ(args[0])
                    .execute();

            JSONObject httpResponse = new JSONObject(response);

            System.out.println(httpResponse.get("videoId"));
            */

            voiceChannel = message.getMessageAuthor().getConnectedVoiceChannel().get();

            EmbedBuilder LoadingEmbed = new EmbedBuilder()
                    .setTitle("Loading selected track! " + IEmojis.Loading)
                    .setColor(EmbedColors.NEUTRAL.getCode())
                    .setFooter("", message.getMessageAuthor().getAvatar());

            LoadingMessage = message.getChannel().sendMessage(LoadingEmbed);
        }
        catch (NoSuchElementException | NullPointerException exception)
        {
            EmbedBuilder NotConnectedEmbed = new EmbedBuilder()
                    .setTitle("You are not currently in a voice channel! " + IEmojis.RedTick)
                    .setDescription("For the bot to work, you have to be in a voice channel")
                    .setColor(EmbedColors.WARNING.getCode())
                    .setFooter("", message.getMessageAuthor().getAvatar());
            message.getChannel().sendMessage(NotConnectedEmbed);

            new Log().log(LogTypes.WARNING, "User not connected to a voice channel!", "Play_Command", exception);
        }

        CompletableFuture<Message> finalLoadingMessage = LoadingMessage;

       assert voiceChannel != null;
        voiceChannel.connect().thenAccept(audioConnection -> {
            // Create a player manager
            AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
            playerManager.registerSourceManager(new YoutubeAudioSourceManager());
            AudioPlayer player = playerManager.createPlayer();

            // Create an audio source and add it to the audio connection's queue
            AudioSource source = new LavaPlayerAudioSource(message.getApi(), player);
            audioConnection.setAudioSource(source);

            // You can now use the AudioPlayer like you would normally do with Lavaplayer, e.g.,
            playerManager.loadItem("https://www.youtube.com/watch?v=NvS351QKFV4", new AudioLoadResultHandler() {
                private Long trackTime;

                @Override
                public void trackLoaded(AudioTrack track)
                {
                    try
                    {
                        trackTime = track.getDuration();
                        String trackDuration =  String.format("%02d:%02d:%02d",
                                //Hours
                                TimeUnit.MILLISECONDS.toHours(trackTime) -
                                        TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(trackTime)),
                                //Minutes
                                TimeUnit.MILLISECONDS.toMinutes(trackTime) -
                                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(trackTime)),
                                //Seconds
                                TimeUnit.MILLISECONDS.toSeconds(trackTime) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(trackTime)));

                        finalLoadingMessage.get().delete();

                        EmbedBuilder TrackPlayingEmbed = new EmbedBuilder()
                                .setTitle("`SongName` is now playing! " + IEmojis.GreenTick)
                                .setDescription("`Duration`: " + "**" + trackDuration + "**")
                                .setColor(EmbedColors.SUCCESS.getCode())
                                .setFooter("", message.getMessageAuthor().getAvatar());

                        message.getChannel().sendMessage(TrackPlayingEmbed);
                    }
                    catch (InterruptedException | ExecutionException e)
                    {
                        new Log().log(LogTypes.WARNING, "problem deleting loading embed", "Play_Command");
                    }

                    player.playTrack(track);

                    new Log().log(LogTypes.INFO, "Playing music...", "Play_Command");
                }

                @Override
                public void playlistLoaded(AudioPlaylist playlist)
                {
                    for (AudioTrack track : playlist.getTracks())
                    {
                        player.playTrack(track);
                    }
                }

                @Override
                public void noMatches()
                {
                   EmbedBuilder noMatchesEmbed = new EmbedBuilder()
                           .setTitle("No matches found! " + IEmojis.RedTick)
                           .setFooter("try and search for something else?", message.getMessageAuthor().getAvatar())
                           .setColor(EmbedColors.WARNING.getCode());
                   message.getChannel().sendMessage(noMatchesEmbed);
                }

                @Override
                public void loadFailed(FriendlyException throwable)
                {
                    EmbedBuilder loadFailedEmbed = new EmbedBuilder()
                            .setTitle("failed to load `SONGNAME`! " + IEmojis.RedTick)
                            .setDescription("Internal error while processing track. Error for devs: " + throwable.toString())
                            .setColor(EmbedColors.ERROR.getCode())
                            .setFooter("", message.getMessageAuthor().getAvatar());

                    message.getChannel().sendMessage(loadFailedEmbed);
                    new Log().log(LogTypes.ERROR, "Error while parsing track", "Play_Command", throwable);
                }
            });
        }).exceptionally(e -> {
            EmbedBuilder NoPermsEmbed = new EmbedBuilder()
                    .setTitle("Failed to join voice channel! " + IEmojis.RedTick)
                    .setDescription("Make sure the bot has voice permissions")
                    .setColor(EmbedColors.ERROR.getCode())
                    .setFooter("", message.getMessageAuthor().getAvatar());
            message.getChannel().sendMessage(NoPermsEmbed);

            new Log().log(LogTypes.ERROR, "Failed to join voice channel on " + message.getServer().toString(), "Play_Command", e);
            return null;
        });
    }
}
