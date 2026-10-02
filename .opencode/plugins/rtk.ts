import type { Plugin } from "@opencode-ai/plugin";

// RTK OpenCode plugin — menulis ulang perintah shell agar memakai `rtk`
// (hemat token 60-90%). File ini auto-load dari `.opencode/plugins/`.
// Syarat: rtk di PATH (`rtk --version` >= 0.27; sudah terinstal via Homebrew).
// Logika rewrite tunggal ada di `rtk rewrite`; file ini hanya delegasi tipis.
// Alternatif global: `rtk init -g --opencode` (menulis ~/.config/opencode/plugins/).
const RtkPlugin: Plugin = async ({ $ }) => {
  return {
    "tool.execute.before": async (input: any, output: any) => {
      try {
        if (input?.tool !== "bash") return;
        const cmd = String(output?.args?.command ?? input?.args?.command ?? "").trim();
        if (!cmd || cmd.startsWith("rtk ")) return;
        const probe = await $`rtk rewrite ${cmd}`.text().catch(() => null);
        const rewritten = String(probe ?? "").trim();
        if (rewritten && rewritten !== cmd && output?.args) {
          output.args.command = rewritten;
        }
      } catch {
        // fallback: biarkan perintah asli jalan
      }
    },
  };
};

export { RtkPlugin };
export default RtkPlugin;
