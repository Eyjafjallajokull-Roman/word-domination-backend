package com.mpls.mainservice.config.discord;

import discord4j.core.GatewayDiscordClient;


public class DiscordAction {

    private final GatewayDiscordClient gatewayDiscordClient;


    public DiscordAction(GatewayDiscordClient gatewayDiscordClient) {
        this.gatewayDiscordClient = gatewayDiscordClient;
    }

    public static DiscordAction of(GatewayDiscordClient gatewayDiscordClient) {
        return new DiscordAction(gatewayDiscordClient);
    }

    public void moveUserInChanel(String nameUser, String channelName) {
        gatewayDiscordClient.getUsers().subscribe(user -> {
            gatewayDiscordClient.getGuilds().subscribe(guild -> {
                guild.getChannels().subscribe(channel -> {
                    if (channel.getName().equalsIgnoreCase(channelName)) {
                        user.asMember(guild.getId()).subscribe(member -> {
                            System.out.println(member.getNickname().orElse(user.getUsername()) + " " + user.getUsername() + " " + guild.getName() + " " + channel.getName());
                            if (nameUser.equalsIgnoreCase(member.getNickname().orElse(user.getUsername()))) {
                                member.edit(guildMemberEditSpec -> {
                                    guildMemberEditSpec.setNewVoiceChannel(channel.getId());
                                }).subscribe();
                            }
                        });
                    }
                });
            });

        });
    }


    public void moveAllUserInChanel(String channelName) {
        gatewayDiscordClient.getUsers().subscribe(user -> {
            if (!user.isBot()) {
                gatewayDiscordClient.getGuilds().subscribe(guild -> {
                    guild.getChannels().subscribe(channel -> {
                        if (channel.getName().equalsIgnoreCase(channelName)) {
                            user.asMember(guild.getId()).subscribe(member -> {
                                System.out.println(member.getNickname().orElse(user.getUsername()) + " " + user.getUsername() + " " + guild.getName() + " " + channel.getName());
                                member.edit(guildMemberEditSpec -> {
                                    guildMemberEditSpec.setNewVoiceChannel(channel.getId());
                                }).subscribe();
                            });
                        }
                    });
                });
            }
        });
    }

}
