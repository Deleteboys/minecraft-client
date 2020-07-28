package de.crazymemecoke.command.commands;

import de.crazymemecoke.command.Command;
import de.crazymemecoke.utils.Notify;
import de.crazymemecoke.utils.Wrapper;

public class Allow extends Command {
    String syntax = ".allow <flight/edit> <true/false>";

    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("flight")) {
                if (args[1].equalsIgnoreCase("true")) {
                    Wrapper.mc.thePlayer.capabilities.allowFlying = true;
                } else if (args[1].equalsIgnoreCase("false")) {
                    Wrapper.mc.thePlayer.capabilities.allowFlying = false;
                } else {
                    Notify.chatMessage(syntax);
                }
            } else if (args[0].equalsIgnoreCase("edit")) {
                if (args[1].equalsIgnoreCase("true")) {
                    Wrapper.mc.thePlayer.capabilities.allowEdit = true;
                } else if (args[1].equalsIgnoreCase("false")) {
                    Wrapper.mc.thePlayer.capabilities.allowEdit = false;
                } else {
                    Notify.chatMessage(syntax);
                }
            } else {
                Notify.chatMessage(syntax);
            }
        } else {
            Notify.chatMessage(syntax);
        }
    }

    @Override
    public String getName() {
        return "allow";
    }
}