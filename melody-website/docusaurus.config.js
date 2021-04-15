/** @type {import('@docusaurus/types').DocusaurusConfig} */
module.exports = {
	title: "Melody Documentation",
	tagline: "The fast, efficent and easy-to-use music bot.",
	url: "https://your-docusaurus-test-site.com",
	baseUrl: "/",
	onBrokenLinks: "throw",
	onBrokenMarkdownLinks: "warn",
	favicon: "img/favicon.ico",
	organizationName: "disbots inc.",
	projectName: "Melody Bot",
	themeConfig: {
		navbar: {
			title: "Melody Docs",
			logo: {
				alt: "Site Logo",
				src: "img/logo.svg",
			},
			items: [
				{
					to: "docs/",
					activeBasePath: "docs",
					label: "Docs",
					position: "left",
				},
				{ to: "blog", label: "Blog", position: "left" },
				{
					href: "https://github.com/DisBots-Studios-Inc/Melody-Bot",
					label: "GitHub",
					position: "right",
				},
				{
					href: "https://twitter.com/GlideGame",
					label: "Game Glide's twitter",
					position: "right",
				},
				{
					href: "https://twitter.com/aktindo",
					label: "Aktindo's twitter",
					position: "right",
				},
			],
		},
		footer: {
			style: "dark",
			links: [
				{
					title: "Docs",
					items: [
						{
							label: "Getting Started",
							to: "docs/",
						},
					],
				},
				{
					title: "Community",
					items: [
						{
							label: "Discord",
							href: "https://discordapp.com/invite/docusaurus",
						},
					],
				},
				{
					title: "More",
					items: [
						{
							label: "Blog",
							to: "blog",
						},
						{
							label: "GitHub",
							href: "https://github.com/DisBots-Studios-Inc/Melody-Bot",
						},
					],
				},
			],
			copyright: `Copyright © ${new Date().getFullYear()} Melody-Bot, disbots Inc. Built with Docusaurus.`,
		},
	},
	presets: [
		[
			"@docusaurus/preset-classic",
			{
				docs: {
					sidebarPath: require.resolve("./sidebars.js"),
					// Please change this to your repo.
					editUrl:
						"https://github.com/facebook/docusaurus/edit/master/website/",
				},
				blog: {
					showReadingTime: true,
					// Please change this to your repo.
					editUrl:
						"https://github.com/facebook/docusaurus/edit/master/website/blog/",
				},
				theme: {
					customCss: require.resolve("./src/css/custom.css"),
				},
			},
		],
	],
};
