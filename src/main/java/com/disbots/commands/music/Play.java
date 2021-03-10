package com.disbots.commands.music;

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
import org.javacord.api.audio.AudioConnection;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;

public class Play implements CommandExecutor
{
    @Command(aliases = {"play", "pl"}, description = "Plays specified music track", usage = "music [music name]", showInHelpPage = false)
    public void OnPlayCommand(MessageCreateEvent message, String[] args)
    {
        ServerVoiceChannel voiceChannel = message.getMessageAuthor().getConnectedVoiceChannel().get();
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
                    player.playTrack(track);
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
