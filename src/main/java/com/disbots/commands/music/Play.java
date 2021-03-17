package com.disbots.commands.music;

import com.disbots.util.EmbedColors;
import com.disbots.util.logging.Log;
import com.disbots.util.logging.LogTypes;
import com.disbots.util.music.LavaPlayerAudioSource;
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

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Play implements CommandExecutor
{
    @Command(aliases = {"play", "pl"}, description = "Plays specified music track", usage = "play <music name>")
    public void OnPlayCommand(MessageCreateEvent message, String[] args)
    {
        EmbedBuilder LoadingEmbed = new EmbedBuilder()
                .setTitle("Loading selected track! <a:loading:781376656937713676>")
                .setColor(EmbedColors.NEUTRAL.getCode())
                .setFooter("", message.getMessageAuthor().getAvatar());

        CompletableFuture<Message> LoadingMessage = message.getChannel().sendMessage(LoadingEmbed);

        ServerVoiceChannel voiceChannel;
        voiceChannel = message.getMessageAuthor().getConnectedVoiceChannel().get();
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
                @Override
                public void trackLoaded(AudioTrack track)
                {
                    try
                    {
                        LoadingMessage.get().delete();

                        EmbedBuilder TrackPlayingEmbed = new EmbedBuilder()
                                .setTitle("`SongName` is now playing! <a:green_tick:781083389280911370>")
                                .setDescription("`Duration`: **TODO**")
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
                    // Notify the user that we've got nothing
                }

                @Override
                public void loadFailed(FriendlyException throwable)
                {
                    // Notify the user that everything exploded
                }
            });
        }).exceptionally(e -> {
            // Failed to connect to voice channel (no permissions?)
            e.printStackTrace();
            return null;
        });
        //TODO: Implement youtube search api
    }
}
